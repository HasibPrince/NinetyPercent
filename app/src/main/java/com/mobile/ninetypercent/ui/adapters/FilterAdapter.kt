package com.mobile.ninetypercent.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.databinding.ItemNameCheckboxBinding

class FilterAdapter(
    var filterItems: List<Pair<Value, Boolean>>,
    val selectListener: (Value, Boolean) -> Unit
) : RecyclerView.Adapter<FilterAdapter.SizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNameCheckboxBinding.inflate(inflater)
        return SizeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filterItems.size
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bind(filterItems[position], selectListener)
    }

    class SizeViewHolder(private val binding: ItemNameCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(filter: Pair<Value, Boolean>, selectListener: (Value, Boolean) -> Unit) {
            binding.sizeText.text = filter.first.value() as String
            binding.sizeCheck.setOnCheckedChangeListener(null)
            binding.sizeCheck.isChecked = filter.second
            binding.root.postDelayed({
                binding.sizeCheck.setOnCheckedChangeListener { buttonView, isChecked ->
                    selectListener(filter.first, isChecked)
                }
            }, 100)
        }
    }
}