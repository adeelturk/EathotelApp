package com.turk.restaurantslist.presentation

sealed class RestaurantEvent {

    data object FetchRestaurants : RestaurantEvent()
    data class FetchRestaurantDetailById(val id:String) : RestaurantEvent()

}