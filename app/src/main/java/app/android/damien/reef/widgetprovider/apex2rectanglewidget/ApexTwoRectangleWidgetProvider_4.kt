package app.android.damien.reef.widgetprovider.apex2rectanglewidget

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
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexTwoRectangleWidgetProvider_4 : AppWidgetProvider(){

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            Log.d("ApexTwoRectangleWidgetProvider", "onUpdate$appWidgetId")
            SharedPreferences.init(context!!)
            var data: List<ApexTwoRectangleWidgets>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao()
                    .readApexTwoRectangleWidgetBackground()

                val views = RemoteViews(context.packageName, R.layout.two_rectangle_widget)

                if (data.lastIndex >= 3) {
                    views.setTextViewText(R.id.value, data[3].topRectangleValue.toString())
                    views.setTextViewText(R.id.value2, data[3].bottomRectangleValue.toString())

                    views.setTextViewText(R.id.unit, data[3].topRectangleUnit)
                    views.setTextViewText(R.id.unit2, data[3].bottomRectangleUnit)

                    views.setInt(R.id.card2, "setBackgroundColor", data[3].topRectangleColor)
                    views.setInt(R.id.card2, "setBackgroundColor", data[3].bottomRectangleColor)
                } else {
                    views.setTextViewText(R.id.value, "NaN")
                    views.setTextViewText(R.id.value2, "NaN")

                    views.setTextViewText(R.id.unit, "Unit 2")
                    views.setTextViewText(R.id.unit2, "Unit 2")
                }

                views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedApex", ""))
                views.setTextViewText(
                    R.id.timestamp2,
                    SharedPreferences.read("lastUpdatedApex", "")
                )

                val intent = Intent(context, ApexTwoRectangleWidgetProvider_4::class.java)
                intent.action = Constants.UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.two_rectangle_widget_relative_layout_apex,
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
            Log.d("ApexTwoRectangleWidgetProvider", "ApexTwoRectangleWidgetProvider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexTwoRectangleWidgetProvider_4::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}