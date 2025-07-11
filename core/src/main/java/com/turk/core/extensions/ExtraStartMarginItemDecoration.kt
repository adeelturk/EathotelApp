package com.turk.core.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ExtraStartMarginItemDecoration(
    private val spaceHeight: Int
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position == 0) {
            with(outRect) { left = spaceHeight + 20 }
        }
    }
}