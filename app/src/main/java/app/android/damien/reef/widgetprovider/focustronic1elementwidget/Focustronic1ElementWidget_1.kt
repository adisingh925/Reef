package app.android.damien.reef.widgetprovider.focustronic1elementwidget

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
import app.android.damien.reef.database_model.FocustronicOneElementWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class Focustronic1ElementWidget_1 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data : List<FocustronicOneElementWidgetModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getFocustronicResponse(this)
            }.invokeOnCompletion {
                Data().updateFocustronicWidgetData(
                    context,
                    JSONArray(SharedPreferences.read("focustronicData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readFocustronicOneElementWidgetBackground()

                val views = RemoteViews(context.packageName, R.layout.custom_widget_layout)

                if(data.lastIndex != 0){
                    views.setTextViewText(R.id.heading, "NaN")
                    views.setTextViewText(R.id.value, "0.0")
                    views.setTextViewText(R.id.unit, "Unit")
                }else{
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
                    views.setInt(R.id.custom_widget_layout_card, "setBackgroundColor", data[0].backgroundColor)
                }

                val intent = Intent(context, Focustronic1ElementWidget_1::class.java)
                intent.action = Constants.UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.custom_widget_layout_card,
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
            Log.d("Focustronic1ElementWidget", "Focustronic1ElementWidget tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, Focustronic1ElementWidget_1::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}