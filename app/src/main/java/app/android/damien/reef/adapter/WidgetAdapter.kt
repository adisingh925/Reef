package app.android.damien.reef.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.R
import app.android.damien.reef.database_model.CustomWidgetModel
import app.android.damien.reef.utils.Constants

class WidgetAdapter(private val context: Context, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var widgetList: MutableList<CustomWidgetModel> = ArrayList()
    interface OnItemClickListener {
        fun onCustomWidgetClick(data: CustomWidgetModel)
    }

    private inner class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var parameter = itemView.findViewById<TextView>(R.id.parameter)
        val value = itemView.findViewById<TextView>(R.id.value)
        val unit = itemView.findViewById<TextView>(R.id.unit)
        val card = itemView.findViewById<LinearLayout>(R.id.custom_widget_layout_card)

        fun bind(position: Int) {
            parameter.text = widgetList[position].parameter
            value.text = widgetList[position].value.toString()
            unit.text = widgetList[position].unit
            card.setBackgroundColor(widgetList[position].color)

            itemView.setOnClickListener {
                onItemClickListener.onCustomWidgetClick(widgetList[position])
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when(widgetList[position].widgetType){
                Constants.CUSTOM -> {
                    (holder as ViewHolder1).bind(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            1 -> return ViewHolder1(
                LayoutInflater.from(context).inflate(R.layout.custom_widget_layout, parent, false)
            )
        }

        return ViewHolder1(
            LayoutInflater.from(context).inflate(R.layout.custom_widget_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(widgetList[position].widgetType){
            Constants.CUSTOM -> {
                (holder as ViewHolder1).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return widgetList.size
    }

    fun setData(widgets : List<CustomWidgetModel>){
        widgetList.clear()
        widgetList.addAll(widgets)
        notifyDataSetChanged()
    }
}