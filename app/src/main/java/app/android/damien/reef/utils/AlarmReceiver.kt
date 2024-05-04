package app.android.damien.reef.utils

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import app.android.damien.reef.database.Database
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.widgetprovider.ApexTwoRectangleWidgetProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class AlarmReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AlarmReceiver", "Alarm received")
        if (context != null) {
            SharedPreferences.init(context)
//            getApexApiResponse(context)
//            getFocustronicAlkatronicApiResponse(context)
            AlarmHelper(context).setExactAndAllowWhileIdleAlarm()
        }
    }

//    private fun getFocustronicAlkatronicApiResponse(context: Context?) {
//        try {
//            CoroutineScope(Dispatchers.IO).launch {
//                val response = ApiClient.apiService.getFocustronicAlkatronicData(
//                    SharedPreferences.read(
//                        "nickname",
//                        ""
//                    ).toString(),
//                    "697"
//                )
//
//                response.enqueue(object : Callback<FocustronicAlkatronicResponse> {
//                    override fun onResponse(
//                        call: Call<FocustronicAlkatronicResponse>,
//                        response: Response<FocustronicAlkatronicResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            if (data != null) {
//                                Log.d("TAG", "onResponse: $data")
//                                val gson = Gson()
//                                val jsonData = gson.toJson(data)
//                                SharedPreferences.write("focustronicAlkatronicData", jsonData)
//                                SharedPreferences.write("lastUpdatedFocustronicAlkatronic", millisToDateTime(System.currentTimeMillis()))
//                                getFocustronicMastertronicApiResponse(context)
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<FocustronicAlkatronicResponse>, t: Throwable) {
//                        t.printStackTrace()
//                    }
//                })
//            }
//        }catch (e: Exception) {
//            Log.d("TAG", "getFocustronicMastertronicApiResponse: " + e.message)
//        }
//    }
//
//    private fun getFocustronicMastertronicApiResponse(context: Context?) {
//        try {
//            CoroutineScope(Dispatchers.IO).launch {
//                val response = ApiClient.apiService.getFocustronicMastertronicData(
//                    SharedPreferences.read(
//                        "nickname",
//                        ""
//                    ).toString(),
//                    "106"
//                )
//
//                response.enqueue(object : Callback<FocustronicMastertronicResponse> {
//                    override fun onResponse(
//                        call: Call<FocustronicMastertronicResponse>,
//                        response: Response<FocustronicMastertronicResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            if (data != null) {
//                                Log.d("TAG", "onResponse: $data")
//                                val gson = Gson()
//                                val jsonData = gson.toJson(data)
//                                SharedPreferences.write("focustronicMastertronicData", jsonData)
//                                SharedPreferences.write("lastUpdatedFocustronicMastertronic", millisToDateTime(System.currentTimeMillis()))
//                                updateFocustronicWidgetData(context, JSONArray(jsonData))
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<FocustronicMastertronicResponse>, t: Throwable) {
//                        t.printStackTrace()
//                    }
//                })
//            }
//        }catch (e: Exception) {
//            Log.d("TAG", "getFocustronicMastertronicApiResponse: " + e.message)
//        }
//    }
//
//    private fun getApexApiResponse(context: Context?) {
//        try {
//            CoroutineScope(Dispatchers.IO).launch {
//                val response = ApiClient.apiService.getApexData(
//                    SharedPreferences.read(
//                        "nickname",
//                        ""
//                    ).toString()
//                )
//
//                response.enqueue(object : Callback<ApexApiResponse> {
//                    override fun onResponse(
//                        call: Call<ApexApiResponse>,
//                        response: Response<ApexApiResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            if (data != null) {
//                                val gson = Gson()
//                                val jsonData = gson.toJson(data)
//                                SharedPreferences.write("apexData", jsonData)
//                                SharedPreferences.write(
//                                    "lastUpdatedApex",
//                                    millisToDateTime(System.currentTimeMillis())
//                                )
//                                updateApexWidgetsData(context, JSONArray(jsonData))
//                            }
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ApexApiResponse>, t: Throwable) {
//                        t.printStackTrace()
//                    }
//                })
//            }
//        }catch (e: Exception) {
//            Log.d("TAG", "getFocustronicMastertronicApiResponse: " + e.message)
//        }
//    }

    private fun updateFocustronicWidgetData(context: Context?, jsonArray: JSONArray) {
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

    private fun updateApexWidgetsData(context: Context?, apiData : JSONArray) {
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
                val ids: IntArray = AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context, ApexTwoRectangleWidgetProvider::class.java))
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
                            i.slot1 = itemList1.map { it.toFloat() }.sum()
                        }
                    }
                    if(i.slot2SelectedValues != ""){
                        val itemList2 = i.slot2SelectedValues?.split(",")
                        if (itemList2 != null) {
                            i.slot2 = itemList2.map { it.toFloat() }.sum()
                        }
                    }
                    if(i.slot3SelectedValues != ""){
                        val itemList3 = i.slot3SelectedValues?.split(",")
                        if (itemList3 != null) {
                            i.slot3 = itemList3.map { it.toFloat() }.sum()
                        }
                    }
                    Database.getDatabase(context).customWidgetsDao().updateApexPowerValuesWidget(i)
                }
            }
        }catch (e: Exception) {
            Log.d("TAG", "updateApexWidgetsData: " + e.message)
        }
    }

    fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}