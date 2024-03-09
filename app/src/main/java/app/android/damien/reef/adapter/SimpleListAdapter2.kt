package app.android.damien.reef.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.android.damien.reef.databinding.ValuesLayoutBinding

class SimpleListAdapter2(
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<SimpleListAdapter2.MyViewHolder>() {

    lateinit var binding: ValuesLayoutBinding

    interface OnItemClickListener {
        fun onItemClick(data: Pair<String, Int>, position: Int)
    }

    class MyViewHolder(binding: ValuesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val text = binding.value
    }

    private var valuesList = ArrayList<Pair<String, Int>>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ValuesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.text = valuesList[position].first

        if (valuesList[position].second == 1) {
            holder.text.setTypeface(null, Typeface.BOLD)
        } else {
            holder.text.setTypeface(null, Typeface.NORMAL)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(valuesList[position], position)
            if (valuesList[position].second == 0) {
                holder.text.setTypeface(null, Typeface.BOLD)
                valuesList[position] = Pair(valuesList[position].first, 1)
            } else {
                holder.text.setTypeface(null, Typeface.NORMAL)
                valuesList[position] = Pair(valuesList[position].first, 0)
            }
        }
    }

    override fun getItemCount(): Int {
        return valuesList.size
    }

    fun setData(list: List<Pair<String, Int>>) {
        valuesList.clear()
        valuesList.addAll(list)
    }
}