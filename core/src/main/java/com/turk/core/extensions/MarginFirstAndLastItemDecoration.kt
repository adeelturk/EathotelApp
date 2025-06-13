package com.turk.core.extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginFirstAndLastItemDecoration(
   private val marginStart: Int = 0.toPx(),
   private val marginEnd: Int = 0.toPx(),

) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        val position = parent.getChildAdapterPosition(view)

        if(position==0){

            with(outRect) {

                left = marginStart
            }
        }

        if(position==state.itemCount -1){

            with(outRect) {
                right = marginEnd
            }
        }



    }
}
