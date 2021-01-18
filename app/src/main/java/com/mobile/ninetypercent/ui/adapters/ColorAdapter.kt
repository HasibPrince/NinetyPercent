package com.mobile.ninetypercent.ui.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.databinding.ItemColorBinding
import com.mobile.ninetypercent.databinding.ItemNameCheckboxBinding
import com.mobile.ninetypercent.ui.utils.ViewUtils

class ColorAdapter(
    var sizeItems: List<Pair<Value, Boolean>>,
    val selectListener: (Value, Boolean) -> Unit
) : RecyclerView.Adapter<ColorAdapter.SizeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemColorBinding.inflate(inflater)
        return SizeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return sizeItems.size
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        holder.bind(sizeItems[position], selectListener)
    }

    class SizeViewHolder(private val binding: ItemColorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(size: Pair<Value, Boolean>, selectListener: (Value, Boolean) -> Unit) {
            binding.option = size.first
            val drawable = binding.color.background as GradientDrawable
            if(size.second) {
                drawable.setStroke(ViewUtils.dpToPx(binding.root.context, 3), Color.BLACK)
            } else {
                drawable.setStroke(0, Color.BLACK)
            }
            binding.color.setOnClickListener {
                selectListener(size.first, !size.second)
            }
        }
    }
}