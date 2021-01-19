package com.mobile.ninetypercent.data.model

import androidx.recyclerview.widget.DiffUtil
import com.mobile.ninetypercent.common.extensions.toJsonString
import com.mobile.ninetypercent.data.*

data class Dress(
    val name: String, val price: Double, val colors: List<Colors>,
    val styles: List<Style>, val sizes: List<Sizes>,
    val shape: Shapes, val material: Material,
    val image: String = "https://cdn.shopify.com/s/files/1/0283/1338/7053/products/NCDW-0149-BLACK_030-16-n-p-black_1800x1800.jpg"
) {
    companion object {
        val DIFF_ITEM_CALLBACK = object : DiffUtil.ItemCallback<Dress>() {
            override fun areItemsTheSame(oldItem: Dress, newItem: Dress): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Dress, newItem: Dress): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }
}

//val image: String = "https://rukminim1.flixcart.com/image/714/857/jeiukcw0/dress/g/z/a/m-20160520-xoxo-original-imaemtxy9ts9rsu9.jpeg"
