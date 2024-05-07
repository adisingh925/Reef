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
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexTwoRectangleWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            var data : List<ApexTwoRectangleWidgets> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context!!).customWidgetsDao().readApexTwoRectangleWidgetBackground()
            }.invokeOnCompletion {
                val views = RemoteViews(context?.packageName, R.layout.two_rectangle_widget)

                views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedApex", ""))
                views.setTextViewText(R.id.timestamp2, SharedPreferences.read("lastUpdatedApex", ""))

                views.setTextViewText(R.id.value, data[0].topRectangleValue.toString())
                views.setTextViewText(R.id.value2, data[0].bottomRectangleValue.toString())

                views.setTextViewText(R.id.unit, data[0].topRectangleUnit)
                views.setTextViewText(R.id.unit2, data[0].bottomRectangleUnit)

                views.setInt(R.id.card1, "setBackgroundColor", data[0].topRectangleColor);
                views.setInt(R.id.card2, "setBackgroundColor", data[0].bottomRectangleColor);

                val intent = Intent(context, ApexTwoRectangleWidgetProvider::class.java)
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
            Log.d("ApexTwoRectangleWidgetProvider", "Widget tapped")

            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                updateWidget(context)
            }
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexTwoRectangleWidgetProvider::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}