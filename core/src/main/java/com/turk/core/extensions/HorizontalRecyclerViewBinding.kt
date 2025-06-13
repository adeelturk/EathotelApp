package com.turk.core.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.*


@BindingAdapter(
    value = ["extraMarginForFirstItemHorizontal", "showHorizontalList", "items"],
    requireAll = false
)
fun <T> RecyclerView.bindHorizontalRecyclerViewAdapter(
    extraMarginForFirstItemHorizontal: Boolean = false,
    adapter: ListAdapter<*, *>,
    list: List<T>?
) {
    this.run {
        this.configureHorizontalList(adapter, extraMarginForFirstItemHorizontal, 4)
        this.adapter = adapter
    }
    if (this.adapter is ListAdapter<*, *>) {
        (adapter as ListAdapter<T, *>).submitList(list)

    }
}

@BindingAdapter(
    value = ["marginForFirst", "marginForLast", "showHorizontalListWithHorizontalMargin", "items"],
    requireAll = false
)
fun <T> RecyclerView.bindHorizontalRecyclerViewAdapterWithMarginAtStartAndEnd(
    marginForFirst: Int = 0,
    marginForLast: Int = 0,
    adapter: ListAdapter<*, *>,
    list: List<T>?
) {
    this.run {
        this.configureHorizontalList(adapter, marginForFirst.toPx(), marginForLast.toPx())
        this.adapter = adapter
    }
    if (this.adapter is ListAdapter<*, *>) {
        (adapter as ListAdapter<T, *>).submitList(list)

    }
}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureHorizontalList(
    adapter: ListAdapter<T, VH>,
    marginStart: Int = 0.toPx(),
    marginEnd: Int = 0.toPx()
) {

    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    setHasFixedSize(true)
    addItemMarginsHorizontalAll(marginStart, marginEnd)
    this.adapter = adapter

}

fun <T, VH : RecyclerView.ViewHolder> RecyclerView.configureHorizontalList(
    adapter: ListAdapter<T, VH>,
    extraMarginForFirstItem: Boolean = false,
    margin: Int = 20.toPx(),
) {

    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    setHasFixedSize(true)
    addItemMarginsHorizontal(margin, MarginStrategy.ALL_SIDES, extraMarginForFirstItem)
    this.adapter = adapter

}


fun RecyclerView.addItemMarginsHorizontalAll(
    marginStart: Int = 0.toPx(),
    marginEnd: Int = 0.toPx(),
) {

    addItemDecoration(MarginFirstAndLastItemDecoration(marginStart, marginEnd))


}


fun RecyclerView.addItemMarginsHorizontal(
    spaceHeight: Int,
    marginStrategy: MarginStrategy = MarginStrategy.ONLY_TOP,
    extraMarginForFirstItem: Boolean = false
) {

    if (extraMarginForFirstItem) {
        addItemDecoration(ExtraStartMarginItemDecoration(spaceHeight))
    } else {
        addItemDecoration(MarginItemDecoration(spaceHeight, marginStrategy))
    }

}

