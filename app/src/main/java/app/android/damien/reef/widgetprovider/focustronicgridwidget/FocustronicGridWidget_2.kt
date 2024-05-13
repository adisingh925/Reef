package app.android.damien.reef.widgetprovider.focustronicgridwidget

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
import app.android.damien.reef.database_model.FocustronicGridWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class FocustronicGridWidget_2 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            Log.d("FocustronicGridWidget", "onUpdate")
            SharedPreferences.init(context!!)
            var data : List<FocustronicGridWidgetModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getFocustronicResponse(this)
            }.invokeOnCompletion {
                Data().updateFocustronicWidgetData(
                    context,
                    JSONArray(SharedPreferences.read("focustronicData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao().readFocustronicGridWidgetBackground()

                val views = RemoteViews(context.packageName, R.layout.grid_widget)

                if(data.lastIndex < 1){
                    views.setTextViewText(R.id.value1, "0.0")
                    views.setTextViewText(R.id.value2, "0.0")
                    views.setTextViewText(R.id.value3, "0.0")
                    views.setTextViewText(R.id.value4, "0.0")
                    views.setTextViewText(R.id.value5, "0.0")
                    views.setTextViewText(R.id.value6, "0.0")
                    views.setTextViewText(R.id.value7, "0.0")
                    views.setTextViewText(R.id.value8, "0.0")

                    views.setTextViewText(R.id.unit1, "Unit")
                    views.setTextViewText(R.id.unit2, "Unit")
                    views.setTextViewText(R.id.unit3, "Unit")
                    views.setTextViewText(R.id.unit4, "Unit")
                    views.setTextViewText(R.id.unit5, "Unit")
                    views.setTextViewText(R.id.unit6, "Unit")
                    views.setTextViewText(R.id.unit7, "Unit")
                    views.setTextViewText(R.id.unit8, "Unit")

                    views.setTextViewText(R.id.name1, "NaN")
                    views.setTextViewText(R.id.name2, "NaN")
                    views.setTextViewText(R.id.name3, "NaN")
                    views.setTextViewText(R.id.name4, "NaN")
                    views.setTextViewText(R.id.name5, "NaN")
                    views.setTextViewText(R.id.name6, "NaN")
                    views.setTextViewText(R.id.name7, "NaN")
                    views.setTextViewText(R.id.name8, "NaN")
                }else{
                    views.setTextViewText(R.id.value1, data[1].slot1Value.toString())
                    views.setTextViewText(R.id.value2, data[1].slot2Value.toString())
                    views.setTextViewText(R.id.value3, data[1].slot3Value.toString())
                    views.setTextViewText(R.id.value4, data[1].slot4Value.toString())
                    views.setTextViewText(R.id.value5, data[1].slot5Value.toString())
                    views.setTextViewText(R.id.value6, data[1].slot6Value.toString())
                    views.setTextViewText(R.id.value7, data[1].slot7Value.toString())
                    views.setTextViewText(R.id.value8, data[1].slot8Value.toString())

                    views.setTextViewText(R.id.unit1, data[1].slot1Unit)
                    views.setTextViewText(R.id.unit2, data[1].slot2Unit)
                    views.setTextViewText(R.id.unit3, data[1].slot3Unit)
                    views.setTextViewText(R.id.unit4, data[1].slot4Unit)
                    views.setTextViewText(R.id.unit5, data[1].slot5Unit)
                    views.setTextViewText(R.id.unit6, data[1].slot6Unit)
                    views.setTextViewText(R.id.unit7, data[1].slot7Unit)
                    views.setTextViewText(R.id.unit8, data[1].slot8Unit)

                    if(data[1].slot1GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name1, data[1].slot1ActualName)
                    } else {
                        views.setTextViewText(R.id.name1, data[1].slot1GivenName)
                    }

                    if(data[1].slot2GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name2, data[1].slot2ActualName)
                    } else {
                        views.setTextViewText(R.id.name2, data[1].slot2GivenName)
                    }

                    if(data[1].slot3GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name3, data[1].slot3ActualName)
                    } else {
                        views.setTextViewText(R.id.name3, data[1].slot3GivenName)
                    }

                    if(data[1].slot4GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name4, data[1].slot4ActualName)
                    } else {
                        views.setTextViewText(R.id.name4, data[1].slot4GivenName)
                    }

                    if(data[1].slot5GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name5, data[1].slot5ActualName)
                    } else {
                        views.setTextViewText(R.id.name5, data[1].slot5GivenName)
                    }

                    if(data[1].slot6GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name6, data[1].slot6ActualName)
                    } else {
                        views.setTextViewText(R.id.name6, data[1].slot6GivenName)
                    }

                    if(data[1].slot7GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name7, data[1].slot7ActualName)
                    } else {
                        views.setTextViewText(R.id.name7, data[1].slot7GivenName)
                    }

                    if(data[1].slot8GivenName.isNullOrEmpty()) {
                        views.setTextViewText(R.id.name8, data[1].slot8ActualName)
                    } else {
                        views.setTextViewText(R.id.name8, data[1].slot8GivenName)
                    }
                }

                val intent = Intent(context, FocustronicGridWidget_2::class.java)
                intent.action = Constants.UPDATE_WIDGET_ACTION
                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                views.setOnClickPendingIntent(
                    R.id.grid_widget_linear_layout,
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
            Log.d("FocustronicGridWidget", "FocustronicGridWidget tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, FocustronicGridWidget_2::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}