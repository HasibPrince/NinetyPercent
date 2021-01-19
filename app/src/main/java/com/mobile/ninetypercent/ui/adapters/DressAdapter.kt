package com.mobile.ninetypercent.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mobile.ninetypercent.data.Colors
import com.mobile.ninetypercent.data.Value
import com.mobile.ninetypercent.data.model.Dress
import com.mobile.ninetypercent.databinding.ItemDressBinding
import com.mobile.ninetypercent.ui.DressViewModel
import com.mobile.ninetypercent.ui.utils.ViewUtils

class DressAdapter(private val dressViewModel: DressViewModel) : ListAdapter<Dress, DressAdapter.DressViewHolder>(Dress.DIFF_ITEM_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDressBinding.inflate(inflater)
        return DressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DressViewHolder, position: Int) {
        val dress = getItem(position)
        holder.bind(dress, dressViewModel.getDressColorsWithSelected(dress))
    }

    class DressViewHolder(val binding: ItemDressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dress: Dress, colors: List<Pair<Value,Boolean>>) {
            binding.dress = dress
            setupColorFilter(colors)
        }

        private fun setupColorFilter(colors: List<Pair<Value,Boolean>>) {
            val colorAdapter = ColorDressAdapter(colors)
            val flexboxLayoutManager = FlexboxLayoutManager(binding.root.context)
            flexboxLayoutManager.flexDirection = FlexDirection.ROW
            flexboxLayoutManager.justifyContent = JustifyContent.CENTER

            setupRecyclerView(binding.dressColors, colorAdapter, flexboxLayoutManager, 10, 10)
        }

        private fun <V : RecyclerView.ViewHolder, T : RecyclerView.Adapter<V>> setupRecyclerView(
            recyclerView: RecyclerView,
            adapter: T,
            layoutManager: RecyclerView.LayoutManager,
            itemLeftMarginInDp: Int, itemBottomMarginInDp: Int
        ) {
            recyclerView.layoutManager = layoutManager
            recyclerView.addItemDecoration(
                ItemDecoration(
                    ViewUtils.dpToPx(binding.root.context, itemBottomMarginInDp),
                    ViewUtils.dpToPx(binding.root.context, itemLeftMarginInDp)
                )
            )
            recyclerView.adapter = adapter
        }
    }

}