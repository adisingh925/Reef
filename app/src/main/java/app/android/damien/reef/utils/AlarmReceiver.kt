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
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
                Data().getFocustronicResponse(this)
            }.invokeOnCompletion {
                try {
                    Data().updateFocustronicWidgetData(
                        context,
                        JSONArray(SharedPreferences.read("focustronicData", "").toString())
                    )
                    Data().updateApexWidgetsData(
                        context,
                        JSONArray(SharedPreferences.read("apexData", "").toString())
                    )
                } catch (e: Exception) {
                    Log.e("AlarmReceiver", e.toString())
                }
            }
            AlarmHelper(context).setExactAndAllowWhileIdleAlarm()
        }
    }

    fun millisToDateTime(millis: Long): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault())
        return dateTime.format(formatter)
    }
}