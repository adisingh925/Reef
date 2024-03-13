package app.android.damien.reef.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import app.android.damien.reef.database.Database
import app.android.damien.reef.model.ApexApiResponse
import app.android.damien.reef.retrofit.ApiClient
import app.android.damien.reef.storage.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

class AlarmReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("AlarmReceiver", "Alarm received")
        if (context != null) {
            getApexApiResponse(context)
            AlarmHelper(context).setExactAndAllowWhileIdleAlarm()
        }
    }

    private fun getApexApiResponse(context: Context?) {
        if (context != null) {
            SharedPreferences.init(context)
        }

        val response = ApiClient.apiService.getApexData(
            SharedPreferences.read(
                Constants.APEX.toString() + "nickname",
                ""
            ).toString()
        )

        response.enqueue(object : Callback<ApexApiResponse> {
            override fun onResponse(
                call: Call<ApexApiResponse>,
                response: Response<ApexApiResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        val gson = Gson()
                        val jsonData = gson.toJson(data)
                        SharedPreferences.write("apexData", jsonData)
                        SharedPreferences.write(
                            "lastUpdatedApex",
                            millisToDateTime(System.currentTimeMillis())
                        )
                        updateApexWidgetsData(context, JSONArray(jsonData))
                        updateFocustronicWidgetData(context, JSONArray(jsonData))
                    }
                }
            }

            override fun onFailure(call: Call<ApexApiResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun updateFocustronicWidgetData(context: Context?, jsonArray: JSONArray) {

    }

    private fun updateApexWidgetsData(context: Context?, apiData : JSONArray) {
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
    }

    fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}