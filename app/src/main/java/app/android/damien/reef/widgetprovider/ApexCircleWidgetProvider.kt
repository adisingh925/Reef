package app.android.damien.reef.widgetprovider

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApexCircleWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            var data : List<ApexCircleWidgetModel> = emptyList()
            CoroutineScope(Dispatchers.IO).launch {
                data = Database.getDatabase(context!!).customWidgetsDao().readApexCircleWidgetBackground()
            }.invokeOnCompletion {
                val views = RemoteViews(context?.packageName, R.layout.circle_widgets)
                views.setTextViewText(R.id.slot1value, data[0].slot1Value.toString())
                views.setTextViewText(R.id.slot2value, data[0].slot2Value.toString())
                views.setTextViewText(R.id.slot3value, data[0].slot3Value.toString())

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

                appWidgetManager?.updateAppWidget(appWidgetId, views)
            }
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }
}