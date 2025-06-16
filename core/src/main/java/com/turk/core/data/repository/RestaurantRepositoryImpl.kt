package com.turk.core.data.repository

import com.turk.core.data.sources.RestaurantDataSource
import com.turk.core.domain.repository.RestaurantRepository

class RestaurantRepositoryImpl(private val restaurantDataSource: RestaurantDataSource) :
    RestaurantRepository {

    override fun getRestaurants(searchQuery:String) =
        restaurantDataSource.getRestaurants(searchQuery)


    override fun getRestaurantDetailById(restaurantId: String) =
        restaurantDataSource.getRestaurantDetailById(restaurantId)


}