package com.turk.core.domain.model

import androidx.recyclerview.widget.DiffUtil

data class Restaurant(
    val id: String?,
    val name: String?,
    val location: String?,
    val rating:Float?,
    val priceLevel:Int?,
    val cuisine:String?,
    val imageUrl:String?
){

    companion object DIFF_CALLBACK : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }
    }
}
