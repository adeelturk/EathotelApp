package com.turk.restaurantslist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turk.core.domain.network.Result
import com.turk.restaurantslist.domain.GetRestaurantsDetailsUseCase
import com.turk.restaurantslist.domain.GetRestaurantsListUseCase
import com.turk.restaurantslist.presentation.restaurnatDetail.RestaurantDetailState
import com.turk.restaurantslist.presentation.restuaurantList.RestaurantsListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class RestaurantViewModel(
    private val getRestaurantsListUseCase: GetRestaurantsListUseCase,
    private val getRestaurantsDetailsUseCase: GetRestaurantsDetailsUseCase
) : ViewModel() {

    fun onEvent(event: RestaurantEvent) {

        when (event) {
            is RestaurantEvent.FetchRestaurants -> {
                getRestaurants()
            }

            is RestaurantEvent.FetchRestaurantDetailById->{

                getRestaurantDetailById(event.id)
            }

        }
    }

    private val _restaurantListState: MutableStateFlow<RestaurantsListState> =
        MutableStateFlow(RestaurantsListState.Nothing)
    val restaurantListState = _restaurantListState.asStateFlow().onStart {
        getRestaurants()
    }


    private fun getRestaurants() {

        getRestaurantsListUseCase().onEach {
            when (it) {
                is Result.Error -> {
                    _restaurantListState.value = RestaurantsListState.Error(it.error)
                }

                is Result.Loading -> {
                    _restaurantListState.value = RestaurantsListState.Loading(it.isLoading)
                }

                is Result.Success -> {
                    _restaurantListState.value = RestaurantsListState.Success(it.data)
                }
            }

        }.launchIn(viewModelScope)
    }

    private val _restaurantDetailState: MutableStateFlow<RestaurantDetailState> =
        MutableStateFlow(RestaurantDetailState.Nothing)
    val restaurantDetailState = _restaurantDetailState.asStateFlow()

    private fun getRestaurantDetailById(restaurantId: String) {

        getRestaurantsDetailsUseCase(restaurantId).onEach {
            when (it) {
                is Result.Error -> {
                    _restaurantDetailState.value = RestaurantDetailState.Error(it.error)
                }

                is Result.Loading -> {
                    _restaurantDetailState.value = RestaurantDetailState.Loading(it.isLoading)
                }

                is Result.Success -> {
                    _restaurantDetailState.value = RestaurantDetailState.Success(it.data)
                }
            }

        }.launchIn(viewModelScope)
    }


}