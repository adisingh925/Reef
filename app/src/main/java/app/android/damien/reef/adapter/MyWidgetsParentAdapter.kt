package app.android.damien.reef.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.databinding.MyWidgetsParentItemLayoutBinding
import app.android.damien.reef.model.MyWidgetsChildModel
import app.android.damien.reef.model.MyWidgetsParentModel
import app.android.damien.reef.utils.Constants

class MyWidgetsParentAdapter(
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MyWidgetsParentAdapter.MyViewHolder>() {

    lateinit var binding: MyWidgetsParentItemLayoutBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyWidgetsChildAdapter
    var myWidgetsList = emptyList<MyWidgetsParentModel>()

    interface OnItemClickListener {
        fun onItemClick(data: MyWidgetsParentModel)
    }

    class MyViewHolder(binding: MyWidgetsParentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val heading = binding.childItemHeading
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = MyWidgetsParentItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
//        createRecyclerView()
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myWidgetsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        when (myWidgetsList[position].widgetType) {
            Constants.APEX_CIRCLE_WIDGET -> {
                holder.heading.text = "Apex Circle Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.APEX_WATER_QUALITY_WIDGET -> {
                holder.heading.text = "Apex Water Quality Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.APEX_POWER_VALUE_WIDGET -> {
                holder.heading.text = "Apex Power Value Widget"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.APEX_FLASK_BACKGROUND_WIDGET -> {
                holder.heading.text = "Apex Flask Background Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.APEX_TWO_RECTANGLE_WIDGET -> {
                holder.heading.text = "Apex Two Rectangle Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.APEX_SINGLE_VALUE_TYPE_2_WIDGET -> {
                holder.heading.text = "Apex Single Value Type 2 Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.APEX_SINGLE_VALUE_TYPE_1_WIDGET -> {
                holder.heading.text = "Apex Single Value Type 1 Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.FOCUSTRONIC_GRID_WIDGET -> {
                holder.heading.text = "Focustronic Grid Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.FOCUSTRONIC_ONE_ELEMENT_WIDGET -> {
                holder.heading.text = "Focustronic One Element Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.FOCUSTRONIC_TWO_RECTANGLE_WIDGET -> {
                holder.heading.text = "Focustronic Two Rectangle Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_1_WIDGET -> {
                holder.heading.text = "Focustronic Single Value Type 1 Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            Constants.FOCUSTRONIC_SINGLE_VALUE_TYPE_2_WIDGET -> {
                holder.heading.text = "Focustronic Single Value Type 2 Widgets"
//                adapter.setData(myWidgetsList[position].widgets)
            }

            else -> {
                "default value"
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(myWidgetsList[position])
        }
    }

//    private fun createRecyclerView() {
//        adapter = MyWidgetsChildAdapter(this.context, object : MyWidgetsChildAdapter.OnItemClickListener {
//                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
//                    Log.d("MyWidgetsParentAdapter", "onCustomWidgetClick: ${data.widgetType}")
//                }
//            })
//        recyclerView = binding.childItemRecyclerview
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//    }

    fun setData(data: List<MyWidgetsParentModel>) {
        myWidgetsList = data
        notifyDataSetChanged()
    }
}