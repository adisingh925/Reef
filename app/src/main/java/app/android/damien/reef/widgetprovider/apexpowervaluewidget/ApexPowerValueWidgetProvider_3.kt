package app.android.damien.reef.widgetprovider.apexpowervaluewidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.util.Locale

class ApexPowerValueWidgetProvider_3 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data : List<ApexPowerValuesWidgetModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readApexPowerValuesWidgetBackground()
                val views = RemoteViews(context.packageName, R.layout.power_value_widget)

                if(data.lastIndex >= 2){
                    val slot1Value = String.format(Locale.getDefault(), "%.2f", data[2].slot1)
                    val slot2Value = String.format(Locale.getDefault(), "%.2f", data[2].slot2)
                    val slot3Value = String.format(Locale.getDefault(), "%.2f", data[2].slot3)

                    views.setTextViewText(R.id.value1, slot1Value)
                    views.setTextViewText(R.id.value2, slot2Value)
                    views.setTextViewText(R.id.value3, slot3Value)
                }else{
                    views.setTextViewText(R.id.value1, "0.0")
                    views.setTextViewText(R.id.value2, "0.0")
                    views.setTextViewText(R.id.value3, "0.0")
                }

                val intent = Intent(context, ApexPowerValueWidgetProvider_3::class.java)
                intent.action = Constants.UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.apex_power_widget_relative_layout,
                    pendingIntent
                )

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == Constants.UPDATE_WIDGET_ACTION) {
            // Handle widget tap here
            Log.d("ApexFlaskBackgroundWidgetProvider", "ApexFlaskBackgroundWidgetProvider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexPowerValueWidgetProvider_3::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}