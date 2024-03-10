package app.android.damien.reef.widgetprovider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.storage.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApexWaterQualityWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            var data : List<ApexWaterQualityWidget> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context!!).customWidgetsDao().readApexWaterQualityWidgetBackground()
            }.invokeOnCompletion {
                val views = RemoteViews(context?.packageName, R.layout.water_quality_widget)
                views.setTextViewText(R.id.slot1value, data[0].slot1Value.toString())
                views.setTextViewText(R.id.slot2value, data[0].slot2Value.toString())
                views.setTextViewText(R.id.slot3value, data[0].slot3Value.toString())
                views.setTextViewText(R.id.slot4value, data[0].slot4Value.toString())
                views.setTextViewText(R.id.slot5value, data[0].slot5Value.toString())

                views.setTextViewText(R.id.slot1unit, data[0].slot1Unit)
                views.setTextViewText(R.id.slot2unit, data[0].slot2Unit)
                views.setTextViewText(R.id.slot3unit, data[0].slot3Unit)
                views.setTextViewText(R.id.slot4unit, data[0].slot4Unit)
                views.setTextViewText(R.id.slot5unit, data[0].slot5Unit)

                views.setTextViewText(R.id.timestamp, SharedPreferences.read("lastUpdatedApex", ""))

                if(data[0].slot1GivenName.isNullOrBlank()){
                    if(data[0].slot1ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot1name, "Slot 1")
                    } else {
                        views.setTextViewText(R.id.slot1name, data[0].slot1ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot1name, data[0].slot1GivenName)
                }

                if(data[0].slot2GivenName.isNullOrBlank()){
                    if(data[0].slot2ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot2name, "Slot 2")
                    } else {
                        views.setTextViewText(R.id.slot2name, data[0].slot2ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot2name, data[0].slot2GivenName)
                }

                if(data[0].slot3GivenName.isNullOrBlank()){
                    if(data[0].slot3ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot3name, "Slot 3")
                    } else {
                        views.setTextViewText(R.id.slot3name, data[0].slot3ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot3name, data[0].slot3GivenName)
                }

                if(data[0].slot4GivenName.isNullOrBlank()){
                    if(data[0].slot4ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot4name, "Slot 4")
                    } else {
                        views.setTextViewText(R.id.slot4name, data[0].slot4ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot4name, data[0].slot4GivenName)
                }

                if(data[0].slot5GivenName.isNullOrBlank()){
                    if(data[0].slot5ActualName.equals("NaN")){
                        views.setTextViewText(R.id.slot5name, "Slot 5")
                    } else {
                        views.setTextViewText(R.id.slot5name, data[0].slot5ActualName)
                    }
                } else {
                    views.setTextViewText(R.id.slot5name, data[0].slot5GivenName)
                }

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }

}