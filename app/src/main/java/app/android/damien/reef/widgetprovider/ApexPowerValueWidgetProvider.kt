package app.android.damien.reef.widgetprovider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApexPowerValueWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            var data : List<ApexPowerValuesWidgetModel> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context!!).customWidgetsDao().readApexPowerValuesWidgetBackground()
            }.invokeOnCompletion {
                val views = RemoteViews(context?.packageName, R.layout.power_value_widget)
                views.setTextViewText(R.id.value1, data[0].slot1.toString())
                views.setTextViewText(R.id.value2, data[0].slot2.toString())
                views.setTextViewText(R.id.value3, data[0].slot3.toString())

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }
}