package com.turk.restaurantslist.domain

import com.turk.core.domain.repository.RestaurantRepository

class GetRestaurantsListUseCase(private val restaurantRepository: RestaurantRepository) {
     operator fun invoke() = restaurantRepository.getRestaurants()
}