package com.mobile.ninetypercent.data.model

import androidx.recyclerview.widget.DiffUtil
import com.mobile.ninetypercent.common.extensions.toJsonString
import com.mobile.ninetypercent.data.*

data class Dress(
    val name: String, val price: String, val colors: List<Colors>,
    val styles: List<Style>, val sizes: List<Sizes>,
    val shape: Shapes, val material: Material
) {
    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Dress>() {
            override fun areItemsTheSame(oldItem: Dress, newItem: Dress): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Dress, newItem: Dress): Boolean {
                return oldItem.toJsonString().contentEquals(newItem.toJsonString())
            }

        }
    }
}