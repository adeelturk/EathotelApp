package com.turk.restaurantslist.presentation.restuaurantList

import com.turk.core.domain.model.Restaurant
import com.turk.core.domain.network.ErrorEntity

sealed class RestaurantsListState {

    data object Nothing : RestaurantsListState()
    data class Loading(val isLoading:Boolean) : RestaurantsListState()
    data class Success(val restaurants: List<Restaurant>) : RestaurantsListState()
    data class Error(val error: ErrorEntity) : RestaurantsListState()

}