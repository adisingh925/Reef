package app.android.damien.reef.widgetprovider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApexSingleValueType1Provider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            var data : List<ApexSingleValueTypeOneModel> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context!!).customWidgetsDao().readApexSingleValueTypeOneWidgetBackground()
            }.invokeOnCompletion {
                val views = RemoteViews(context?.packageName, R.layout.single_value_type_1_apex)

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

                views.setTextColor(R.id.value, data[0].textColor)
                views.setTextColor(R.id.heading, data[0].textColor)
                views.setTextColor(R.id.unit, data[0].textColor)

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }

}