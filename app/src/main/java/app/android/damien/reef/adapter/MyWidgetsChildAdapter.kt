package app.android.damien.reef.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.R
import app.android.damien.reef.database_model.ApexCircleWidgetModel
import app.android.damien.reef.database_model.ApexFlaskBackgroundWidgetModel
import app.android.damien.reef.database_model.ApexPowerValuesWidgetModel
import app.android.damien.reef.database_model.ApexSingleValueTypeOneModel
import app.android.damien.reef.database_model.ApexSingleValueTypeTwoModel
import app.android.damien.reef.database_model.ApexTwoRectangleWidgets
import app.android.damien.reef.database_model.ApexWaterQualityWidget
import app.android.damien.reef.model.MyWidgetsChildModel
import app.android.damien.reef.utils.Constants

class MyWidgetsChildAdapter(
    private val context: Context,
    private val widgetType : String,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var setApexFlaskBackgroundWidgetData = emptyList<ApexFlaskBackgroundWidgetModel>()
    private var setApexPowerValuesWidgetData = emptyList<ApexPowerValuesWidgetModel>()
    private var setApexCircleWidgetData = emptyList<ApexCircleWidgetModel>()
    private var setApexTwoRectangleWidgetData = emptyList<ApexTwoRectangleWidgets>()
    private var setApexSingleValueTypeOneWidgetData = emptyList<ApexSingleValueTypeOneModel>()
    private var setApexSingleValueTypeTwoWidgetData = emptyList<ApexSingleValueTypeTwoModel>()
    private var setApexWaterQualityWidgetData = emptyList<ApexWaterQualityWidget>()

    interface OnItemClickListener {
        fun onApexFlaskBackgroundWidgetClick(data: ApexFlaskBackgroundWidgetModel)
        fun onApexPowerValuesWidgetClick(data: ApexPowerValuesWidgetModel)
        fun onApexCircleWidgetClick(data: ApexCircleWidgetModel)
        fun onApexTwoRectangleWidgetClick(data: ApexTwoRectangleWidgets)
        fun onApexSingleValueTypeOneWidgetClick(data: ApexSingleValueTypeOneModel)
        fun onApexSingleValueTypeTwoWidgetClick(data: ApexSingleValueTypeTwoModel)
        fun onApexWaterQualityWidgetClick(data: ApexWaterQualityWidget)
    }

    private inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {


        }
    }

    private inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {


        }
    }

    private inner class ViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val slot1Value = itemView.findViewById<TextView>(R.id.slot1value)
        val slot2Value = itemView.findViewById<TextView>(R.id.slot2value)
        val slot3Value = itemView.findViewById<TextView>(R.id.slot3value)
        val slot1name = itemView.findViewById<TextView>(R.id.slot1text)
        val slot2name = itemView.findViewById<TextView>(R.id.slot2text)
        val slot3name = itemView.findViewById<TextView>(R.id.slot3text)

        fun bind(position: Int) {

            slot1name.setText(setApexFlaskBackgroundWidgetData[position].slot1Name)
            slot2name.setText(setApexFlaskBackgroundWidgetData[position].slot2Name)
            slot3name.setText(setApexFlaskBackgroundWidgetData[position].slot3Name)
            slot1Value.setText(setApexFlaskBackgroundWidgetData[position].slot1Value.toString())
            slot2Value.setText(setApexFlaskBackgroundWidgetData[position].slot2Value.toString())
            slot3Value.setText(setApexFlaskBackgroundWidgetData[position].slot3Value.toString())

            itemView.setOnClickListener {
                onItemClickListener.onApexFlaskBackgroundWidgetClick(setApexFlaskBackgroundWidgetData[position])
            }
        }
    }

    private inner class ViewHolder5(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder6(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder7(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder8(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder9(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder10(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder11(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    private inner class ViewHolder12(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    override fun getItemViewType(position: Int): Int {
        when (widgetType) {
            Constants.APEX_POWER_VALUE_WIDGET -> return 1
            Constants.APEX_TWO_RECTANGLE_WIDGET -> return 2
            Constants.APEX_CIRCLE_WIDGET -> return 3
            Constants.APEX_FLASK_BACKGROUND_WIDGET -> return 4
            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> return 5
            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> return 6
            Constants.APEX_WATER_QUALITY_WIDGET -> return 7
            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> return 8
            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> return 9
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> return 10
            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> return 11
            Constants.FOCUSTRONIC_GRID_WIDGET -> return 12
        }

        Log.d("MyWidgetsChildAdapter", "getItemViewType: ${widgetType}")

        return 1
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            Log.d("MyWidgetsChildAdapter", "onBindViewHolder: ${widgetType}")
            when (widgetType) {
                Constants.APEX_POWER_VALUE_WIDGET -> {
                    (holder as ViewHolder1).bind(position)
                }

                Constants.APEX_TWO_RECTANGLE_WIDGET -> {
                    (holder as ViewHolder2).bind(position)
                }

                Constants.APEX_CIRCLE_WIDGET -> {
                    (holder as ViewHolder3).bind(position)
                }

                Constants.APEX_FLASK_BACKGROUND_WIDGET -> {
                    (holder as ViewHolder4).bind(position)
                }

                Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> {
                    (holder as ViewHolder5).bind(position)
                }

                Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> {
                    (holder as ViewHolder6).bind(position)
                }

                Constants.APEX_WATER_QUALITY_WIDGET -> {
                    (holder as ViewHolder7).bind(position)
                }

                Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> {
                    (holder as ViewHolder8).bind(position)
                }

                Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> {
                    (holder as ViewHolder9).bind(position)
                }

                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> {
                    (holder as ViewHolder10).bind(position)
                }

                Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> {
                    (holder as ViewHolder11).bind(position)
                }

                Constants.FOCUSTRONIC_GRID_WIDGET -> {
                    (holder as ViewHolder12).bind(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        Log.d("MyWidgetsChildAdapter", "onCreateViewHolder: $viewType")

        when (viewType) {
            1 -> return ViewHolder1(
                LayoutInflater.from(context).inflate(R.layout.power_value_widget, parent, false)
            )

            2 -> return ViewHolder2(
                LayoutInflater.from(context).inflate(R.layout.two_rectangle_widget, parent, false)
            )

            3 -> return ViewHolder3(
                LayoutInflater.from(context).inflate(R.layout.circle_widgets, parent, false)
            )

            4 -> return ViewHolder4(
                LayoutInflater.from(context).inflate(R.layout.flask_background_widget, parent, false)
            )

            5 -> return ViewHolder5(
                LayoutInflater.from(context).inflate(R.layout.single_value_type_1_apex, parent, false)
            )

            6 -> return ViewHolder6(
                LayoutInflater.from(context).inflate(R.layout.single_value_type_2_apex, parent, false)
            )

            7 -> return ViewHolder7(
                LayoutInflater.from(context).inflate(R.layout.water_quality_widget, parent, false)
            )

            8 -> return ViewHolder8(
                LayoutInflater.from(context).inflate(R.layout.custom_widget_layout, parent, false)
            )

            9 -> return ViewHolder9(
                LayoutInflater.from(context).inflate(R.layout.two_rectangle_widget, parent, false)
            )

            10 -> return ViewHolder10(
                LayoutInflater.from(context).inflate(R.layout.single_value_type_1, parent, false)
            )

            11 -> return ViewHolder11(
                LayoutInflater.from(context).inflate(R.layout.single_value_type_2, parent, false)
            )

            12 -> return ViewHolder12(
                LayoutInflater.from(context).inflate(R.layout.grid_widget, parent, false)
            )
        }

        return ViewHolder1(
            LayoutInflater.from(context).inflate(R.layout.power_value_widget, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (widgetType) {
            Constants.APEX_POWER_VALUE_WIDGET -> {
                (holder as ViewHolder1).bind(position)
            }

            Constants.APEX_TWO_RECTANGLE_WIDGET -> {
                (holder as ViewHolder2).bind(position)
            }

            Constants.APEX_CIRCLE_WIDGET -> {
                (holder as ViewHolder3).bind(position)
            }

            Constants.APEX_FLASK_BACKGROUND_WIDGET -> {
                (holder as ViewHolder4).bind(position)
            }

            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> {
                (holder as ViewHolder5).bind(position)
            }

            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> {
                (holder as ViewHolder6).bind(position)
            }

            Constants.APEX_WATER_QUALITY_WIDGET -> {
                (holder as ViewHolder7).bind(position)
            }

            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> {
                (holder as ViewHolder8).bind(position)
            }

            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> {
                (holder as ViewHolder9).bind(position)
            }

            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> {
                (holder as ViewHolder10).bind(position)
            }

            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> {
                (holder as ViewHolder11).bind(position)
            }

            Constants.FOCUSTRONIC_GRID_WIDGET -> {
                (holder as ViewHolder12).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return setApexFlaskBackgroundWidgetData.size
    }

    fun setApexFlaskBackgroundWidgetData(widgets: List<ApexFlaskBackgroundWidgetModel>) {
        setApexFlaskBackgroundWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexPowerValuesWidgetData(widgets: List<ApexPowerValuesWidgetModel>) {
        setApexPowerValuesWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexCircleWidgetData(widgets: List<ApexCircleWidgetModel>) {
        setApexCircleWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexTwoRectangleWidgetData(widgets: List<ApexTwoRectangleWidgets>) {
        setApexTwoRectangleWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexSingleValueTypeOneWidgetData(widgets: List<ApexSingleValueTypeOneModel>) {
        setApexSingleValueTypeOneWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexSingleValueTypeTwoWidgetData(widgets: List<ApexSingleValueTypeTwoModel>) {
        setApexSingleValueTypeTwoWidgetData = widgets
        notifyDataSetChanged()
    }

    fun setApexWaterQualityWidgetData(widgets: List<ApexWaterQualityWidget>) {
        setApexWaterQualityWidgetData = widgets
        notifyDataSetChanged()
    }
}