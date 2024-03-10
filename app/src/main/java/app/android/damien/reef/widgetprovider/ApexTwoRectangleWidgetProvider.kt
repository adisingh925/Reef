package app.android.damien.reef.widgetprovider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.storage.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }
}