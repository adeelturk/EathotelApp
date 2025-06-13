package com.turk.core.domain.model

import androidx.recyclerview.widget.DiffUtil


data class RestaurantDetail(
    val name: String?,
    val location: String?,
    val rating:Float?,
    val priceLevel:Int?,
    val cuisineDetail:String?,
    val imageUrl:String?,
    val description:String?,
    var labels: ArrayList<String> = arrayListOf(),
    var otherRestaurantOfSameCuisine: List<Restaurant> = arrayListOf(),
    var otherRestaurantInSameArea: List<Restaurant> = arrayListOf()
){



}