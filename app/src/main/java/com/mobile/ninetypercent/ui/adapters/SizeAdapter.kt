package com.mobile.ninetypercent.ui.adapters

import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.databinding.ItemNameCheckboxBinding
import java.util.logging.Handler

class SizeAdapter(
    var sizeItems: List<Pair<Value, Boolean>>,
    val selectListener: (Value, Boolean) -> Unit
) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNameCheckboxBinding.inflate(inflater)
        return SizeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sizeItems.size
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bind(sizeItems[position], selectListener)
    }

    class SizeViewHolder(private val binding: ItemNameCheckboxBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(size: Pair<Value, Boolean>, selectListener: (Value, Boolean) -> Unit) {
            binding.sizeText.text = size.first.value() as String
            binding.sizeCheck.setOnCheckedChangeListener(null)
            binding.sizeCheck.isChecked = size.second
            binding.root.postDelayed({
                binding.sizeCheck.setOnCheckedChangeListener { buttonView, isChecked ->
                    selectListener(size.first, isChecked)
                }
            }, 100)

//            binding.sizeCheck.setOnClickListener { view ->
//                selectListener(size.first, !size.second)
//            }

        }
    }
}