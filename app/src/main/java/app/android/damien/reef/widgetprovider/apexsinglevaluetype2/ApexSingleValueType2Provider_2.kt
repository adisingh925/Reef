package app.android.damien.reef.widgetprovider.apexsinglevaluetype2

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
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexSingleValueType2Provider_2 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data : List<ApexSingleValueTypeTwoModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readApexSingleValueTypeTwoWidgetBackground()

                val views = RemoteViews(context.packageName, R.layout.single_value_type_2_apex)

                if(data.lastIndex < 1){
                    views.setTextViewText(R.id.heading, "NaN")
                    views.setTextViewText(R.id.value, "0.0")
                    views.setTextViewText(R.id.unit, "Unit")
                }else{
                    if(data[1].givenName.isNullOrBlank()){
                        if(data[1].actualName.equals("NaN")){
                            views.setTextViewText(R.id.heading, "NaN")
                        } else {
                            views.setTextViewText(R.id.heading, data[1].actualName)
                        }
                    } else {
                        views.setTextViewText(R.id.heading, data[1].givenName)
                    }

                    views.setTextViewText(R.id.value, data[1].value.toString())
                    views.setTextViewText(R.id.unit, data[1].unit.toString())
                    views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedApex", ""))

                    views.setTextColor(R.id.value, data[1].textColor)
                    views.setTextColor(R.id.heading, data[1].textColor)
                    views.setTextColor(R.id.unit, data[1].textColor)
                    views.setTextColor(R.id.timestamp, data[1].textColor)
                }
                
                val intent = Intent(context, ApexSingleValueType2Provider_2::class.java)
                intent.action = Constants.UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.layout,
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
            Log.d("ApexSingleValueType2Provider", "ApexSingleValueType2Provider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexSingleValueType2Provider_2::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}