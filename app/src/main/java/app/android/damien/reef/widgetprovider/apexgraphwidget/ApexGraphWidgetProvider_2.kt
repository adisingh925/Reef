package app.android.damien.reef.widgetprovider.apexgraphwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import app.android.damien.reef.R
import app.android.damien.reef.database.Database
import app.android.damien.reef.database_model.ApexGraphWidgetModel
import app.android.damien.reef.storage.SharedPreferences
import app.android.damien.reef.utils.Constants
import app.android.damien.reef.utils.Data
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray

class ApexGraphWidgetProvider_2 : AppWidgetProvider() {

    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach { appWidgetId ->
            SharedPreferences.init(context!!)
            var data: List<ApexGraphWidgetModel>
            CoroutineScope(Dispatchers.IO).launch {
                Data().getApexData(this)
            }.invokeOnCompletion {
                Data().updateApexWidgetsData(
                    context,
                    JSONArray(SharedPreferences.read("apexData", "").toString())
                )
                data = Database.getDatabase(context).customWidgetsDao()
                    .readApexGraphBackground()
                CoroutineScope(Dispatchers.Main).launch {
                    val views = RemoteViews(context.packageName, R.layout.graph_widget)

                    if (data.lastIndex >= 1) {
                        val graph = data[1].records?.split(",")?.map { it.toFloat() }

                        val maxValue = graph?.maxOrNull()
                        val minValue = graph?.minOrNull()

                        views.setTextViewText(R.id.lowervalue, minValue.toString())
                        views.setTextViewText(R.id.uppervalue, maxValue.toString())
                        views.setTextViewText(R.id.unit, data[1].unit)
                        views.setTextViewText(R.id.value, data[1].value.toString())
                        views.setTextViewText(R.id.heading, data[1].actualName)

                        val entries = ArrayList<Entry>()
                        graph?.forEachIndexed { index, value ->
                            entries.add(Entry((index + 1).toFloat(), value))
                        }

                        val dataSet = LineDataSet(entries, "Data Set")
                        dataSet.color = Color.WHITE // Set color of the line to white
                        dataSet.lineWidth = 5f // Increase the line width to 5 pixels
                        dataSet.setDrawValues(false) // Hide values on points

                        val lineData = LineData(dataSet)

                        val chart = LineChart(context)
                        chart.setBackgroundColor(Color.parseColor("#cc7700"))
                        chart.data = lineData

                        // Hide the description
                        chart.description.isEnabled = false

                        // Hide X and Y axis
                        chart.axisLeft.isEnabled = false
                        chart.axisRight.isEnabled = false
                        chart.xAxis.isEnabled = false

                        // Hide legend
                        chart.legend.isEnabled = false

                        chart.measure(
                            View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY),
                            View.MeasureSpec.makeMeasureSpec(500, View.MeasureSpec.EXACTLY)
                        )
                        chart.layout(0, 0, chart.measuredWidth, chart.measuredHeight)

                        val chartBitmap = chart.getChartBitmap()

                        views.setImageViewBitmap(R.id.graph, chartBitmap)
                    } else {
                        views.setTextViewText(R.id.lowervalue, "0.0")
                        views.setTextViewText(R.id.uppervalue, "0.0")
                        views.setTextViewText(R.id.unit, "Unit")
                        views.setTextViewText(R.id.value, "0.0")
                        views.setTextViewText(R.id.heading, "NaN")
                    }

                    val intent = Intent(context, ApexGraphWidgetProvider_2::class.java)
                    intent.action = Constants.UPDATE_WIDGET_ACTION
                    val pendingIntent = PendingIntent.getBroadcast(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )

                    views.setOnClickPendingIntent(
                        R.id.graphg_widget_layout_card,
                        pendingIntent
                    )

                    appWidgetManager?.updateAppWidget(appWidgetId, views)
                }
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        if (intent?.action == Constants.UPDATE_WIDGET_ACTION) {
            // Handle widget tap here
            Log.d("ApexGraphWidgetProvider_2", "ApexGraphWidgetProvider_2 tapped")

            SharedPreferences.init(context!!)
            updateWidget(context)
        }
    }

    private fun updateWidget(context: Context?) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(context!!, ApexGraphWidgetProvider_2::class.java)
        )
        onUpdate(context, appWidgetManager, appWidgetIds)
    }
}