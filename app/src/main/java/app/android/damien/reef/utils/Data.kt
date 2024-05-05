package app.android.damien.reef.utils

import android.util.Log
import app.android.damien.reef.model.alkatronic.AlkatronicData
import app.android.damien.reef.model.apex.ApexData
import app.android.damien.reef.model.aquariumdevices.AquariumDevices
import app.android.damien.reef.model.aquariumtanks.AquariumTanks
import app.android.damien.reef.model.mastertronic.MastertronicData
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class Data {

    private val jsonArray = JSONArray()
    private val focustronicJsonObject = JSONObject()

    suspend fun getApexData(coroutineScope: CoroutineScope): String {
        val resultDeferred = CompletableDeferred<String>()

        coroutineScope.launch(Dispatchers.IO) {
            val cookie = SharedPreferences.read("${Constants.APEX}cookie", "").toString()

            if (cookie.isEmpty()) {
                resultDeferred.complete("Cookie is empty")
                return@launch
            }

            try {
                val response = ApiClient.apexApiService.getApexData(
                    "connect.sid=$cookie"
                ).execute()

                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        val inputs = JSONArray(data.string())
                            .getJSONObject(0)
                            .getJSONObject("status")
                            .getJSONArray("inputs")

                        val jsonArray = JSONArray()
                        val jsonObject = JSONObject()
                        for (i in 0 until inputs.length()) {
                            val input = inputs.getJSONObject(i)
                            jsonObject.put(
                                input.getString("name").toLowerCase(Locale.ROOT),
                                input.getString("value")
                            )
                        }
                        jsonArray.put(jsonObject)
                        SharedPreferences.write("apexData", jsonArray.toString())
                        SharedPreferences.write(
                            "lastUpdatedApex",
                            millisToDateTime(System.currentTimeMillis())
                        )
                        resultDeferred.complete("Apex data retrieved successfully")
                    }
                } else {
                    resultDeferred.completeExceptionally(HttpException(response))
                }
            } catch (e: Exception) {
                resultDeferred.completeExceptionally(e)
            }
        }

        return resultDeferred.await()
    }

    suspend fun getFocustronicResponse(coroutineScope: CoroutineScope): String {
        val deferredResult = CompletableDeferred<String>()

        val cookie = SharedPreferences.read("${Constants.FOCUSTRONIC}cookie", "").toString()

        if (cookie.isEmpty()) {
            return "Focustronic cookie is empty"
        }

        coroutineScope.launch {
            try {
                async {
                    getAquariumTanks(cookie, this)
                }.await()
                // Signal completion with the result
                deferredResult.complete("Focustronic response received")
                SharedPreferences.write(
                    "lastUpdatedFocustronic",
                    millisToDateTime(System.currentTimeMillis())
                )
            } catch (e: Exception) {
                // Signal completion with an error
                deferredResult.completeExceptionally(e)
            }
        }.invokeOnCompletion {
            if (it != null) {
                // Signal completion with an error
                deferredResult.completeExceptionally(it)
            }
        }

        // Return the deferred result
        return deferredResult.await()
    }

    private fun getAquariumTanks(cookie: String, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            val aquariumTanks = async { ApiClient.alkatronicApiService.getAquariumTanks(cookie).execute() }
            val data = aquariumTanks.await()
            if (data.isSuccessful) {
                val tanks = data.body()
                if (tanks != null) {
                    val jsonArray = JSONObject(tanks.string()).getJSONArray("data")
                    val deviceJobs = ArrayList<Deferred<Unit>>()

                    for (i in 0 until jsonArray.length()) {
                        val tank = jsonArray.getJSONObject(i)
                        val job = async {
                            getAquariumDevices(tank.getInt("id"), cookie, this)
                        }
                        deviceJobs.add(job)
                    }

                    deviceJobs.awaitAll()
                }
            }
        }
    }

    private suspend fun getAquariumDevices(
        id: Int,
        cookie: String,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            val aquariumDevices = async { ApiClient.alkatronicApiService.getAquariumDevices(cookie, id).execute() }
            val data = aquariumDevices.await()
            if (data.isSuccessful) {
                val devices = data.body()
                if (devices != null) {
                    val jsonObject = JSONObject(devices.string()).getJSONObject("data")
                    val alkatronicsArray = jsonObject.getJSONArray("alkatronics")
                    val mastertronicsArray = jsonObject.getJSONArray("mastertronics")

                    val alkatronicJobs = mutableListOf<Deferred<Unit>>()

                    for (i in 0 until alkatronicsArray.length()) {
                        val alkatronic = alkatronicsArray.getJSONObject(i)
                        val job = async {
                            getAlkatronicData(alkatronic.getInt("id"), cookie, coroutineScope)
                        }
                        alkatronicJobs.add(job)
                    }

                    alkatronicJobs.awaitAll()

                    val mastertronicJobs = mutableListOf<Deferred<Unit>>()

                    for (i in 0 until mastertronicsArray.length()) {
                        val mastertronic = mastertronicsArray.getJSONObject(i)
                        val job = async {
                            getMastertronicData(mastertronic.getInt("id"), cookie, coroutineScope)
                        }
                        mastertronicJobs.add(job)
                    }

                    mastertronicJobs.awaitAll()
                }
            }
        }
    }

    private fun getMastertronicData(id: Int, cookie: String, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            val mastertronicData = async { ApiClient.alkatronicApiService.getMastertronicData(cookie, id).execute() }
            val data = mastertronicData.await()
            if (data.isSuccessful) {
                val mastertronic = data.body()
                if (mastertronic != null) {
                    val mastertronicJsonArray = JSONObject(mastertronic.string())
                        .getJSONObject("data")
                        .getJSONArray("parameters")

                    for (i in 0 until mastertronicJsonArray.length()) {
                        val parameter = mastertronicJsonArray.getJSONObject(i)
                        focustronicJsonObject.put(
                            parameter.getString("parameter"),
                            parameter.getString("value")
                        )
                    }

                    jsonArray.put(0, focustronicJsonObject)
                    SharedPreferences.write("focustronicData", jsonArray.toString())
                }
            }
        }
    }

    private fun getAlkatronicData(id: Int, cookie: String, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            val alkatronicData = async { ApiClient.alkatronicApiService.getAlkatronicData(cookie, id).execute() }
            val data = alkatronicData.await()
            if (data.isSuccessful) {
                val alkatronic = data.body()
                if (alkatronic != null) {
                    val alkatronicJsonArray = JSONObject(alkatronic.string()).getJSONArray("data")
                    for (i in 0 until alkatronicJsonArray.length()) {
                        val alkatronicData = alkatronicJsonArray.getJSONObject(i)
                        focustronicJsonObject.put("kh_value", alkatronicData.getString("kh_value"))
                        focustronicJsonObject.put("ph_value", alkatronicData.getString("ph_value"))
                        focustronicJsonObject.put(
                            "acid_used",
                            alkatronicData.getString("acid_used")
                        )
                    }
                    jsonArray.put(0, focustronicJsonObject)
                    SharedPreferences.write("focustronicData", jsonArray.toString())
                }
            }
        }
    }

    private fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}