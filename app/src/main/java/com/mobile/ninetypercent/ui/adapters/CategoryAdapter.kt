package com.mobile.ninetypercent.ui.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.databinding.ItemCategoryBinding

class CategoryAdapter(
    var categoryList: List<Pair<Value, Boolean>>,
    private val clickListener: (Value, Boolean) -> Unit
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position], clickListener)
    }

    class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            category: Pair<Value, Boolean>,
            clickListener: (Value, Boolean) -> Unit
        ) {
            binding.categoryString = category.first.value() as String
            if (category.second) {
                binding.category.typeface = Typeface.DEFAULT_BOLD
            } else {
                binding.category.typeface = Typeface.DEFAULT
            }

            binding.root.setOnClickListener {
                clickListener(category.first, !category.second)
            }
        }
    }
}