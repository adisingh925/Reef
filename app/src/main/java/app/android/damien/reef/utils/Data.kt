package app.android.damien.reef.utils

import android.util.Log
import app.android.damien.reef.model.alkatronic.AlkatronicData
import app.android.damien.reef.model.apex.ApexData
import app.android.damien.reef.model.aquariumdevices.AquariumDevices
import app.android.damien.reef.model.aquariumtanks.AquariumTanks
import app.android.damien.reef.model.mastertronic.MastertronicData
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class Data {

    val jsonArray = JSONArray()
    val focustronicJsonObject = JSONObject()

    fun getApexData() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apexApiService.getApexData(
                "connect.sid=${
                    SharedPreferences.read(
                        "${Constants.APEX}cookie",
                        ""
                    ).toString()
                }"
            )

            response.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
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
                        }
                    } else {
                        Log.d("ApexData", "Failed to get apex data!")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun getFocustronicResponse() {
        val cookie = SharedPreferences.read("${Constants.FOCUSTRONIC}cookie", "").toString()
        getAquariumTanks(cookie)
    }

    private fun getAquariumTanks(cookie: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val aquariumTanks =
                async { ApiClient.alkatronicApiService.getAquariumTanks(cookie).execute() }
            val data = aquariumTanks.await()
            Log.d("TAG", "getAquariumTanks: $data")
            if (data.isSuccessful) {
                val tanks = data.body()?.data
                if (tanks != null) {
                    for (tank in tanks) {
                        Log.d("TAG", "getAquariumTanks: $tank")
                        getAquariumDevices(tank.id, cookie)
                    }
                }
            }
        }
//        val aquariumTanks = ApiClient.alkatronicApiService.getAquariumTanks(cookie).execute()

//        aquariumTanks.enqueue(object : Callback<AquariumTanks> {
//            override fun onResponse(
//                call: Call<AquariumTanks>,
//                response: Response<AquariumTanks>
//            ) {
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    if (data != null) {
//                        val tanks = data.data
//                        for (tank in tanks) {
//                            getAquariumDevices(tank.id, cookie)
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<AquariumTanks>, t: Throwable) {
//                t.printStackTrace()
//            }
//        })
    }

    private fun getAquariumDevices(id: Int, cookie: String) {
        val aquariumDevices = ApiClient.alkatronicApiService.getAquariumDevices(cookie, id)

        aquariumDevices.enqueue(object : Callback<AquariumDevices> {
            override fun onResponse(
                call: Call<AquariumDevices>,
                response: Response<AquariumDevices>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        for (i in data.data.mastertronics) {
                            getMastertronicData(i.id, cookie)
                        }

                        for (i in data.data.alkatronics) {
                            getAlkatronicData(i.id, cookie)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AquariumDevices>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getMastertronicData(id: Int, cookie: String) {
        val mastertronicData = ApiClient.alkatronicApiService.getMastertronicData(
            cookie,
            id
        )

        mastertronicData.enqueue(object : Callback<MastertronicData> {
            override fun onResponse(
                call: Call<MastertronicData>,
                response: Response<MastertronicData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Log.d("TAG", "onResponse: $data")
                        for (i in data.data.parameters) {
                            focustronicJsonObject.put(i.parameter, i.value)
                        }
                        jsonArray.put(0, focustronicJsonObject)
                        SharedPreferences.write("focustronicData", jsonArray.toString())
                    }
                }
            }

            override fun onFailure(call: Call<MastertronicData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun getAlkatronicData(id: Int, cookie: String) {
        val alkatronicData = ApiClient.alkatronicApiService.getAlkatronicData(
            cookie, id
        )

        alkatronicData.enqueue(object : Callback<AlkatronicData> {
            override fun onResponse(
                call: Call<AlkatronicData>,
                response: Response<AlkatronicData>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Log.d("TAG", "onResponse: $data")
                        for (i in data.data) {
                            focustronicJsonObject.put("kh_value", i.kh_value)
                            focustronicJsonObject.put("ph_value", i.ph_value)
                            focustronicJsonObject.put("acid_used", i.acid_used)
                        }
                        jsonArray.put(0, focustronicJsonObject)
                        SharedPreferences.write("focustronicData", jsonArray.toString())
                    }
                }
            }

            override fun onFailure(call: Call<AlkatronicData>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}