package app.android.damien.reef.widgetprovider.focustronictworectangle

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
import app.android.damien.reef.database_model.FocustronicTwoRectangleWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class Focustronic2RectangleWidget_4 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            try{
                Log.d("FocustronicTwoRectangleWidgetProvider", "onUpdate")
                SharedPreferences.init(context!!)
                var data : List<FocustronicTwoRectangleWidgetModel>
                CoroutineScope(Dispatchers.IO).launch {
                    Data().getFocustronicResponse(this)
                }.invokeOnCompletion {
                    Data().updateFocustronicWidgetData(
                        context,
                        JSONArray(SharedPreferences.read("focustronicData", "").toString())
                    )
                    data = Database.getDatabase(context).customWidgetsDao().readFocustronicDoubleRectangleWidgetBackground()

                    val views = RemoteViews(context.packageName, R.layout.two_rectangle_widget)

                    views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedFocustronic", ""))
                    views.setTextViewText(R.id.timestamp2, SharedPreferences.read("lastUpdatedFocustronic", ""))


                    if(data.lastIndex < 3){
                        views.setTextViewText(R.id.value, "0.0")
                        views.setTextViewText(R.id.value2, "0.0")
                        views.setTextViewText(R.id.unit, "Unit")
                        views.setTextViewText(R.id.unit2, "Unit")
                    }else{
                        views.setTextViewText(R.id.value, data[3].topRectangleValue.toString())
                        views.setTextViewText(R.id.value2, data[3].bottomRectangleValue.toString())

                        views.setTextViewText(R.id.unit, data[3].topRectangleUnit)
                        views.setTextViewText(R.id.unit2, data[3].bottomRectangleUnit)

                        views.setInt(R.id.card1, "setBackgroundColor", data[3].topRectangleColor);
                        views.setInt(R.id.card2, "setBackgroundColor", data[3].bottomRectangleColor);
                    }

                    val intent = Intent(context, Focustronic2RectangleWidget_4::class.java)
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
            }catch (e : Exception) {
                Log.d("Exception in Focustronic2RectangleWidget_4.onUpdate() => ",e.message.toString())
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == Constants.UPDATE_WIDGET_ACTION) {
            // Handle widget tap here
            Log.d("Focustronic2RectangleWidget", "Focustronic2RectangleWidget tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, Focustronic2RectangleWidget_4::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}