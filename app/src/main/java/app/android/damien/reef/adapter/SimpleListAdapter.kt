package app.android.damien.reef.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.databinding.ValuesLayoutBinding

class SimpleListAdapter(private val context: Context, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<SimpleListAdapter.MyViewHolder>() {

    lateinit var binding: ValuesLayoutBinding

    interface OnItemClickListener {
        fun onItemClick(data: String)
    }

    class MyViewHolder(binding: ValuesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val text = binding.value
    }

    private var valuesList = emptyList<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ValuesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = valuesList[position]

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(valuesList[position])
        }
    }

    override fun getItemCount(): Int {
        return valuesList.size
    }

    fun setData(list: List<String>) {
        valuesList = list
        notifyDataSetChanged()
    }
}