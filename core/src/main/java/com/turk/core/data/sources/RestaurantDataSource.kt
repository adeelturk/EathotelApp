package com.turk.core.data.sources

import com.turk.core.domain.model.Restaurant
import com.turk.core.domain.model.RestaurantDetail
import com.turk.core.domain.network.ErrorEntity
import com.turk.core.domain.network.Result
import kotlinx.coroutines.flow.Flow

interface RestaurantDataSource {
     fun getRestaurants(searchQuery:String): Flow<Result<List<Restaurant>, ErrorEntity>>
     fun getRestaurantDetailById(restaurantId: String): Flow<Result<RestaurantDetail, ErrorEntity>>
}
