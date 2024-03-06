package app.android.damien.reef.adapter

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.databinding.ValuesLayoutBinding

class ValuesAdapter(private val context: Context) :
    RecyclerView.Adapter<ValuesAdapter.MyViewHolder>() {

    lateinit var binding: ValuesLayoutBinding

    class MyViewHolder(binding: ValuesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val text = binding.value
    }

    private var valuesList = emptyList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ValuesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return valuesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = valuesList[position]

        holder.itemView.setOnLongClickListener {
            val cliptext = valuesList[position]
            val item = ClipData.Item(cliptext)
            val mimetype = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData(cliptext, mimetype, item)

            val dragShadowBuilder = View.DragShadowBuilder(it)
            it.startDragAndDrop(data, dragShadowBuilder, null, 0)
            true
        }
    }

    fun setData(list: List<String>) {
        valuesList = list
        notifyDataSetChanged()
    }
}