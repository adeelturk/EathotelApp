package com.turk.restaurantslist.domain

import com.turk.core.domain.repository.RestaurantRepository

class GetRestaurantsDetailsUseCase(private val restaurantRepository: RestaurantRepository) {
     operator fun invoke(restaurantId: String) = restaurantRepository.getRestaurantDetailById(restaurantId)
}