package app.android.damien.reef.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.R
import app.android.damien.reef.model.MyWidgetsChildModel
import app.android.damien.reef.utils.Constants

class MyWidgetsChildAdapter(
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var widgetList = emptyList<MyWidgetsChildModel>()

    interface OnItemClickListener {
        fun onCustomWidgetClick(data: MyWidgetsChildModel)
    }

    private inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {

            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder3(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder4(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder5(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder6(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder7(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder8(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder9(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder10(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder11(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    private inner class ViewHolder12(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (widgetList[position].widgetType) {
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
            when (widgetList[position].widgetType) {
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
        when (widgetList[position].widgetType) {
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
        return widgetList.size
    }

    fun setData(widgets: List<MyWidgetsChildModel>) {
        widgetList = widgets
        notifyDataSetChanged()
    }
}