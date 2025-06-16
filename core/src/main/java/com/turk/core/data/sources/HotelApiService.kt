package com.turk.core.data.sources

import com.turk.core.data.model.RestaurantDetailResponse
import com.turk.core.data.model.RestaurantDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HotelApiService {

    @GET("restaurants")
    suspend fun getRestaurants(
        @Query("region_id") regionId: String,
        @Query("q") searchQuery: String,
    ): Response<RestaurantDto>

    @GET("restaurants/{id}")
    suspend fun getRestaurantDetailById(
        @Path("id") restaurantId: String,
    ): Response<RestaurantDetailResponse>

}