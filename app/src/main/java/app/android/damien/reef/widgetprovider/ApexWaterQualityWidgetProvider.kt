package app.android.damien.reef.widgetprovider

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
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexWaterQualityWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data : List<ApexWaterQualityWidget>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readApexWaterQualityWidgetBackground()
                val views = RemoteViews(context.packageName, R.layout.water_quality_widget)
                views.setTextViewText(R.id.slot1value, data[0].slot1Value.toString())
                views.setTextViewText(R.id.slot2value, data[0].slot2Value.toString())
                views.setTextViewText(R.id.slot3value, data[0].slot3Value.toString())
                views.setTextViewText(R.id.slot4value, data[0].slot4Value.toString())
                views.setTextViewText(R.id.slot5value, data[0].slot5Value.toString())

                views.setTextViewText(R.id.slot1unit, data[0].slot1Unit)
                views.setTextViewText(R.id.slot2unit, data[0].slot2Unit)
                views.setTextViewText(R.id.slot3unit, data[0].slot3Unit)
                views.setTextViewText(R.id.slot4unit, data[0].slot4Unit)
                views.setTextViewText(R.id.slot5unit, data[0].slot5Unit)

                views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedApex", ""))

                if(data[0].slot1GivenName.isNullOrBlank()){
                    if(data[0].slot1ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot1name, "Slot 1")
                    } else {
                        views.setTextViewText(R.id.slot1name, data[0].slot1ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot1name, data[0].slot1GivenName)
                }

                if(data[0].slot2GivenName.isNullOrBlank()){
                    if(data[0].slot2ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot2name, "Slot 2")
                    } else {
                        views.setTextViewText(R.id.slot2name, data[0].slot2ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot2name, data[0].slot2GivenName)
                }

                if(data[0].slot3GivenName.isNullOrBlank()){
                    if(data[0].slot3ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot3name, "Slot 3")
                    } else {
                        views.setTextViewText(R.id.slot3name, data[0].slot3ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot3name, data[0].slot3GivenName)
                }

                if(data[0].slot4GivenName.isNullOrBlank()){
                    if(data[0].slot4ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot4name, "Slot 4")
                    } else {
                        views.setTextViewText(R.id.slot4name, data[0].slot4ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot4name, data[0].slot4GivenName)
                }

                if(data[0].slot5GivenName.isNullOrBlank()){
                    if(data[0].slot5ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot5name, "Slot 5")
                    } else {
                        views.setTextViewText(R.id.slot5name, data[0].slot5ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot5name, data[0].slot5GivenName)
                }

                val intent = Intent(context, ApexWaterQualityWidgetProvider::class.java)
                intent.action = Constants.UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.apex_water_quality_widget_relative_layout,
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
            Log.d("ApexWaterQualityWidgetProvider", "ApexWaterQualityWidgetProvider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexWaterQualityWidgetProvider::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }

}