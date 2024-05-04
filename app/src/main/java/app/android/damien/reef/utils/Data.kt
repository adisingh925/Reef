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

            response.enqueue(object : Callback<ApexData> {
                override fun onResponse(
                    call: Call<ApexData>,
                    response: Response<ApexData>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            val inputs = data[0].status.inputs
                            val jsonArray = JSONArray()
                            val jsonObject = JSONObject()
                            for (input in inputs) {
                                jsonObject.put(
                                    input.name.toLowerCase(Locale.ROOT),
                                    input.value.toString()
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

                override fun onFailure(call: Call<ApexData>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun getFocustronicResponse() {
        val cookie = SharedPreferences.read("${Constants.FOCUSTRONIC}cookie", "").toString()
        getAquariumTanks(cookie)
        Log.d("TAG", "hello")
    }

    private fun getAquariumTanks(cookie: String) {
        val aquariumTanks = ApiClient.alkatronicApiService.getAquariumTanks(cookie)

        aquariumTanks.enqueue(object : Callback<AquariumTanks> {
            override fun onResponse(
                call: Call<AquariumTanks>,
                response: Response<AquariumTanks>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        val tanks = data.data
                        for (tank in tanks) {
                            getAquariumDevices(tank.id, cookie)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AquariumTanks>, t: Throwable) {
                t.printStackTrace()
            }
        })
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
                            Log.d("TAG", "Mastertronic")
                            getMastertronicData(i.id, cookie)
                        }

                        for (i in data.data.alkatronics) {
                            Log.d("TAG", "Alkatronic")
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