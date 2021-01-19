package com.mobile.ninetypercent.ui.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ItemDecoration(protected val bottomSpace: Int, protected val leftSpace: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = bottomSpace
        outRect.left = leftSpace
    }
}