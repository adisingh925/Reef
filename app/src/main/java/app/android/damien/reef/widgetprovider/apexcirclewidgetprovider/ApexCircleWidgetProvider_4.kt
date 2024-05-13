package app.android.damien.reef.widgetprovider.apexcirclewidgetprovider

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
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants.UPDATE_WIDGET_ACTION
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexCircleWidgetProvider_4 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data: List<ApexCircleWidgetModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readApexCircleWidgetBackground()
                val views = RemoteViews(context.packageName, R.layout.circle_widgets)
                views.setTextViewText(R.id.slot1value, data[3].slot1Value.toString())
                views.setTextViewText(R.id.slot2value, data[3].slot2Value.toString())
                views.setTextViewText(R.id.slot3value, data[3].slot3Value.toString())

                if (data[3].slot1GivenName.isNullOrBlank()) {
                    if (data[3].slot1ActualName.equals("NaN")) {
                        views.setTextViewText(R.id.slot1name, "Slot 1")
                    } else {
                        views.setTextViewText(R.id.slot1name, data[3].slot1ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot1name, data[3].slot1GivenName)
                }

                if (data[3].slot2GivenName.isNullOrBlank()) {
                    if (data[3].slot2ActualName.equals("NaN")) {
                        views.setTextViewText(R.id.slot2name, "Slot 2")
                    } else {
                        views.setTextViewText(R.id.slot2name, data[3].slot2ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot2name, data[3].slot2GivenName)
                }

                if (data[3].slot3GivenName.isNullOrBlank()) {
                    if (data[3].slot3ActualName.equals("NaN")) {
                        views.setTextViewText(R.id.slot3name, "Slot 3")
                    } else {
                        views.setTextViewText(R.id.slot3name, data[3].slot3ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot3name, data[3].slot3GivenName)
                }

                val intent = Intent(context, ApexCircleWidgetProvider_4::class.java)
                intent.action = UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.apex_circle_widget_relative_layout,
                    pendingIntent
                )

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == UPDATE_WIDGET_ACTION) {
            // Handle widget tap here
            Log.d("ApexCircleWidgetProvider", "ApexCircleWidgetProvider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexCircleWidgetProvider_4::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}