package com.turk.restaurantslist.presentation.restaurnatDetail

import com.turk.core.domain.model.RestaurantDetail
import com.turk.core.domain.network.ErrorEntity

sealed class RestaurantDetailState {

    data object Nothing : RestaurantDetailState()
    data class Loading(val isLoading:Boolean) : RestaurantDetailState()
    data class Success(val restaurantDetail: RestaurantDetail) : RestaurantDetailState()
    data class Error(val error: ErrorEntity) : RestaurantDetailState()

}