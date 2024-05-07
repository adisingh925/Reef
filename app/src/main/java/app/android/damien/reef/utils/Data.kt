package app.android.damien.reef.utils

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import app.android.damien.reef.database.Database
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.widgetprovider.ApexTwoRectangleWidgetProvider
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.HttpException
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

    fun updateFocustronicWidgetData(context: Context?, jsonArray: JSONArray) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val focustronicSingleValueType1 = Database.getDatabase(context!!).customWidgetsDao().readFocustronicSingleValueTypeOneWidgetBackground()

                for(i in focustronicSingleValueType1){
                    if(i.actualName != "NaN"){
                        i.value = jsonArray.getJSONObject(0).get(i.actualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateFocustronicSingleValueTypeOneWidget(i)
                }

                val focustronicSingleValueType2 = Database.getDatabase(context).customWidgetsDao().readFocustronicSingleValueTypeTwoWidgetBackground()

                for(i in focustronicSingleValueType2){
                    if(i.actualName != "NaN"){
                        i.value = jsonArray.getJSONObject(0).get(i.actualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateFocustronicSingleValueTypeTwoWidget(i)
                }

                val focustronicTwoRectangleWidget = Database.getDatabase(context).customWidgetsDao().readFocustronicDoubleRectangleWidgetBackground()

                for(i in focustronicTwoRectangleWidget){
                    if(i.topRectangleActualName != "NaN"){
                        i.topRectangleValue = jsonArray.getJSONObject(0).get(i.topRectangleActualName).toString().toFloat()
                    }
                    if(i.bottomRectangleActualName != "NaN"){
                        i.bottomRectangleValue = jsonArray.getJSONObject(0).get(i.bottomRectangleActualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateFocustronicDoubleRectangleWidget(i)
                }

                val focustronic1ElementWidget = Database.getDatabase(context).customWidgetsDao().readFocustronicOneElementWidgetBackground()

                for(i in focustronic1ElementWidget){
                    if(i.actualName != "NaN"){
                        i.value = jsonArray.getJSONObject(0).get(i.actualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateFocustronicOneElementWidget(i)
                }

                val focustronicGridWidget = Database.getDatabase(context).customWidgetsDao().readFocustronicGridWidgetBackground()

                for(i in focustronicGridWidget){
                    if(i.slot1ActualName != "NaN"){
                        i.slot1Value = jsonArray.getJSONObject(0).get(i.slot1ActualName).toString().toFloat()
                    }
                    if(i.slot2ActualName != "NaN"){
                        i.slot2Value = jsonArray.getJSONObject(0).get(i.slot2ActualName).toString().toFloat()
                    }
                    if(i.slot3ActualName != "NaN"){
                        i.slot3Value = jsonArray.getJSONObject(0).get(i.slot3ActualName).toString().toFloat()
                    }
                    if(i.slot4ActualName != "NaN"){
                        i.slot4Value = jsonArray.getJSONObject(0).get(i.slot4ActualName).toString().toFloat()
                    }
                    if(i.slot5ActualName != "NaN"){
                        i.slot5Value = jsonArray.getJSONObject(0).get(i.slot5ActualName).toString().toFloat()
                    }
                    if(i.slot6ActualName != "NaN"){
                        i.slot6Value = jsonArray.getJSONObject(0).get(i.slot6ActualName).toString().toFloat()
                    }
                    if(i.slot7ActualName != "NaN"){
                        i.slot7Value = jsonArray.getJSONObject(0).get(i.slot7ActualName).toString().toFloat()
                    }
                    if(i.slot8ActualName != "NaN"){
                        i.slot8Value = jsonArray.getJSONObject(0).get(i.slot8ActualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateFocustronicGridWidget(i)
                }
            }
        }catch (e: Exception) {
            Log.d("TAG", "updateFocustronicWidgetData: " + e.message)
        }
    }

    fun updateApexWidgetsData(context: Context?, apiData : JSONArray) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val apexFlaskWidget = Database.getDatabase(context!!).customWidgetsDao().readApexFlaskBackgroundWidgetBackground()
                val jsonObject = JSONObject(apiData.getJSONObject(0).toString())
                for(i in apexFlaskWidget){
                    if(i.slot1ActualName != "NaN"){
                        i.slot1Value = jsonObject.get(i.slot1ActualName).toString().toFloat()
                    }
                    if(i.slot2ActualName != "NaN"){
                        i.slot2Value = jsonObject.get(i.slot2ActualName).toString().toFloat()
                    }
                    if(i.slot3ActualName != "NaN"){
                        i.slot3Value = jsonObject.get(i.slot3ActualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexFlaskBackgroundWidget(i)
                }

                val apexCircleWidget = Database.getDatabase(context).customWidgetsDao().readApexCircleWidgetBackground()
                for(i in apexCircleWidget){
                    if(i.slot1ActualName != "NaN"){
                        i.slot1Value = jsonObject.get(i.slot1ActualName).toString().toFloat()
                    }
                    if(i.slot2ActualName != "NaN"){
                        i.slot2Value = jsonObject.get(i.slot2ActualName).toString().toFloat()
                    }
                    if(i.slot3ActualName != "NaN"){
                        i.slot3Value = jsonObject.get(i.slot3ActualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexCircleWidget(i)
                }

                val apexTwoRectangleWidget = Database.getDatabase(context).customWidgetsDao().readApexTwoRectangleWidgetBackground()
                for(i in apexTwoRectangleWidget){
                    if(i.topRectangleActualName != "NaN"){
                        i.topRectangleValue = jsonObject.get(i.topRectangleActualName).toString().toFloat()
                    }
                    if(i.bottomRectangleActualName != "NaN"){
                        i.bottomRectangleValue = jsonObject.get(i.bottomRectangleActualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexTwoRectangleWidget(i)
                }

                val intent = Intent(
                    context,
                    ApexTwoRectangleWidgetProvider::class.java
                )
                intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
                val ids: IntArray = AppWidgetManager.getInstance(context).getAppWidgetIds(
                    ComponentName(context, ApexTwoRectangleWidgetProvider::class.java)
                )
                intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                context.sendBroadcast(intent)

                val apexSingleValueType1 = Database.getDatabase(context).customWidgetsDao().readApexSingleValueTypeOneWidgetBackground()
                for(i in apexSingleValueType1){
                    if(i.actualName != "NaN"){
                        i.value = jsonObject.get(i.actualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexSingleValueTypeOneWidget(i)
                }

                val apexSingleValueType2 = Database.getDatabase(context).customWidgetsDao().readApexSingleValueTypeTwoWidgetBackground()
                for(i in apexSingleValueType2){
                    if(i.actualName != "NaN"){
                        i.value = jsonObject.get(i.actualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexSingleValueTypeTwoWidget(i)
                }

                val apexWaterQualityWidget = Database.getDatabase(context).customWidgetsDao().readApexWaterQualityWidgetBackground()
                for(i in apexWaterQualityWidget){
                    if(i.slot1ActualName != "NaN"){
                        i.slot1Value = jsonObject.get(i.slot1ActualName).toString().toFloat()
                    }
                    if(i.slot2ActualName != "NaN"){
                        i.slot2Value = jsonObject.get(i.slot2ActualName).toString().toFloat()
                    }
                    if(i.slot3ActualName != "NaN"){
                        i.slot3Value = jsonObject.get(i.slot3ActualName).toString().toFloat()
                    }
                    if(i.slot4ActualName != "NaN"){
                        i.slot4Value = jsonObject.get(i.slot4ActualName).toString().toFloat()
                    }
                    if(i.slot5ActualName != "NaN"){
                        i.slot5Value = jsonObject.get(i.slot5ActualName).toString().toFloat()
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexWaterQualityWidget(i)
                }

                val apexPowerWidgets = Database.getDatabase(context).customWidgetsDao().readApexPowerValuesWidgetBackground()

                for(i in apexPowerWidgets){
                    if(i.slot1SelectedValues != ""){
                        val itemList1 = i.slot1SelectedValues?.split(",")
                        if (itemList1 != null) {
                            for(item in itemList1){
                                i.slot1 += jsonObject.get(item).toString().toFloat()
                            }
                        }
                    }
                    if(i.slot2SelectedValues != ""){
                        val itemList2 = i.slot2SelectedValues?.split(",")
                        if (itemList2 != null) {
                            for(item in itemList2){
                                try {
                                    i.slot2 += jsonObject.get(item).toString().toFloat()
                                }catch (e: Exception){
                                    Log.d("TAG", "updateApexWidgetsData: " + e.message)
                                    i.slot1 += 0.0f
                                }
                            }
                        }
                    }
                    if(i.slot3SelectedValues != ""){
                        val itemList3 = i.slot3SelectedValues?.split(",")
                        if (itemList3 != null) {
                            for(item in itemList3){
                                i.slot3 += jsonObject.get(item).toString().toFloat()
                            }
                        }
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexPowerValuesWidget(i)
                }
            }
        }catch (e: Exception) {
            Log.d("TAG", "updateApexWidgetsData: " + e.message)
        }
    }

    private fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}