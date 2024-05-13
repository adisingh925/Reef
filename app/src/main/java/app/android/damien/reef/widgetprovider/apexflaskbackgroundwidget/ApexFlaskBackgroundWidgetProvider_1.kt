package app.android.damien.reef.widgetprovider.apexflaskbackgroundwidget

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
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexFlaskBackgroundWidgetProvider_1 : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data : List<ApexFlaskBackgroundWidgetModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readApexFlaskBackgroundWidgetBackground()
                val views = RemoteViews(context.packageName, R.layout.flask_background_widget)

                if(data.lastIndex < 0) {
                    views.setTextViewText(R.id.slot1value, "0.0")
                    views.setTextViewText(R.id.slot2value, "0.0")
                    views.setTextViewText(R.id.slot3value, "0.0")

                    views.setTextViewText(R.id.slot1name, "NaN")
                    views.setTextViewText(R.id.slot2name, "NaN")
                    views.setTextViewText(R.id.slot3name, "NaN")
                }else{
                    views.setTextViewText(R.id.slot1value, data[0].slot1Value.toString())
                    views.setTextViewText(R.id.slot2value, data[0].slot2Value.toString())
                    views.setTextViewText(R.id.slot3value, data[0].slot3Value.toString())

                    if(data[0].slot1GivenName.isNullOrBlank()){
                        if(data[0].slot1ActualName.equals("NaN")){
                            views.setTextViewText(R.id.slot1name, "NaN")
                        } else {
                            views.setTextViewText(R.id.slot1name, data[0].slot1ActualName)
                        }
                    } else {
                        views.setTextViewText(R.id.slot1name, data[0].slot1GivenName)
                    }

                    if(data[0].slot2GivenName.isNullOrBlank()){
                        if(data[0].slot2ActualName.equals("NaN")){
                            views.setTextViewText(R.id.slot2name, "NaN")
                        } else {
                            views.setTextViewText(R.id.slot2name, data[0].slot2ActualName)
                        }
                    } else {
                        views.setTextViewText(R.id.slot2name, data[0].slot2GivenName)
                    }

                    if(data[0].slot3GivenName.isNullOrBlank()){
                        if(data[0].slot3ActualName.equals("NaN")){
                            views.setTextViewText(R.id.slot3name, "NaN")
                        } else {
                            views.setTextViewText(R.id.slot3name, data[0].slot3ActualName)
                        }
                    } else {
                        views.setTextViewText(R.id.slot3name, data[0].slot3GivenName)
                    }
                }

                val intent = Intent(context, ApexFlaskBackgroundWidgetProvider_1::class.java)
                intent.action = UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.apex_flask_background_widgets_layout,
                    pendingIntent
                )

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }

    companion object {
        const val UPDATE_WIDGET_ACTION = "UPDATE_WIDGET_ACTION"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == UPDATE_WIDGET_ACTION) {
            // Handle widget tap here
            Log.d("ApexFlaskBackgroundWidgetProvider", "ApexFlaskBackgroundWidgetProvider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexFlaskBackgroundWidgetProvider_1::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}