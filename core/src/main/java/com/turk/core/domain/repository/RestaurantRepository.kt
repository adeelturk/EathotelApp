package com.turk.core.domain.repository

import com.turk.core.domain.model.Restaurant
import com.turk.core.domain.model.RestaurantDetail
import com.turk.core.domain.network.ErrorEntity
import com.turk.core.domain.network.Result
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository{
     fun getRestaurants(): Flow<Result<List<Restaurant>, ErrorEntity>>

     fun getRestaurantDetailById(restaurantId: String): Flow<Result<RestaurantDetail, ErrorEntity>>

}