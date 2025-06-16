package com.turk.restaurantslist.domain

import com.turk.core.domain.repository.RestaurantRepository

class GetRestaurantsListUseCase(private val restaurantRepository: RestaurantRepository) {
     operator fun invoke(searchQuery:String) = restaurantRepository.getRestaurants(searchQuery)
}