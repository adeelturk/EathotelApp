package com.turk.restaurantslist.presentation

sealed class RestaurantEvent {

    data class FetchRestaurants(val searchQuery:String = "") : RestaurantEvent()
    data class FetchRestaurantDetailById(val id:String) : RestaurantEvent()

}