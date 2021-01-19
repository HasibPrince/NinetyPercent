package com.mobile.ninetypercent.common.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.ninetypercent.data.model.Dress
import com.mobile.ninetypercent.ui.adapters.DressAdapter

@BindingAdapter("dresses")
fun RecyclerView.setDresses(dressList: List<Dress>?) {
    dressList?.let {
        (adapter as DressAdapter).submitList(dressList)
    }
}