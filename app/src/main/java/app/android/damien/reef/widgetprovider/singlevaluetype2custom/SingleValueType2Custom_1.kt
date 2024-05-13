package app.android.damien.reef.widgetprovider.singlevaluetype2custom

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
import app.android.damien.reef.database_model.CustomWidgetSingleValueType2Model
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleValueType2Custom_1 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data : List<CustomWidgetSingleValueType2Model>
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context).customWidgetsDao().readCustomSingleValueTypeTwoWidgetBackground()

                val views = RemoteViews(context.packageName, R.layout.single_value_type_2)

                if(data.lastIndex < 0){
                    views.setTextViewText(R.id.heading, "NaN")
                    views.setTextViewText(R.id.value, "0.0")
                    views.setTextViewText(R.id.unit, "Unit")
                }else{
                    views.setTextViewText(R.id.heading, data[0].givenName)
                    views.setTextViewText(R.id.value, data[0].value.toString())
                    views.setTextViewText(R.id.unit, data[0].unit.toString())

                    views.setTextColor(R.id.value, data[0].textColor)
                    views.setTextColor(R.id.heading, data[0].textColor)
                    views.setTextColor(R.id.unit, data[0].textColor)
                }

                val intent = Intent(context, SingleValueType2Custom_1::class.java)
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
            Log.d("ApexSingleValueType1Provider", "ApexSingleValueType1Provider tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, SingleValueType2Custom_1::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}