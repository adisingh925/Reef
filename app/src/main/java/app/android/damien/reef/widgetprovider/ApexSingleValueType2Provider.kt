package app.android.damien.reef.widgetprovider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexSingleValueType2Provider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            var data : List<ApexSingleValueTypeTwoModel> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context!!).customWidgetsDao().readApexSingleValueTypeTwoWidgetBackground()
            }.invokeOnCompletion {
                val views = RemoteViews(context?.packageName, R.layout.single_value_type_2_apex)

                if(data[0].givenName.isNullOrBlank()){
                    if(data[0].actualName.equals("NaN")){
                        views.setTextViewText(R.id.heading, "NaN")
                    } else {
                        views.setTextViewText(R.id.heading, data[0].actualName)
                    }
                } else {
                    views.setTextViewText(R.id.heading, data[0].givenName)
                }

                views.setTextViewText(R.id.value, data[0].value.toString())
                views.setTextViewText(R.id.unit, data[0].unit.toString())
                views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedApex", ""))

                views.setTextColor(R.id.value, data[0].textColor)
                views.setTextColor(R.id.heading, data[0].textColor)
                views.setTextColor(R.id.unit, data[0].textColor)
                views.setTextColor(R.id.timestamp, data[0].textColor)

                val intent = Intent(context, ApexSingleValueType2Provider::class.java)
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
            Log.d("ApexSingleValueType2Provider", "Widget tapped")

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
            ComponentName(context!!, ApexSingleValueType2Provider::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}