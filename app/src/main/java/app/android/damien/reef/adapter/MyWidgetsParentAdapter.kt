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
        createRecyclerView()
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myWidgetsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.heading.text = myWidgetsList[position].heading
        adapter.setData(myWidgetsList[position].widgets)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(myWidgetsList[position])
        }
    }

    private fun createRecyclerView() {
        adapter = MyWidgetsChildAdapter(this.context, object : MyWidgetsChildAdapter.OnItemClickListener {
                override fun onCustomWidgetClick(data: MyWidgetsChildModel) {
                    Log.d("MyWidgetsParentAdapter", "onCustomWidgetClick: ${data.widgetType}")
                }
            })
        recyclerView = binding.childItemRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setData(data: List<MyWidgetsParentModel>) {
        myWidgetsList = data
        notifyDataSetChanged()
    }
}