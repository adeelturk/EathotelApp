package com.turk.core.data.sources

import com.turk.core.data.mapper.mapToRestaurant
import com.turk.core.data.mapper.mapToRestaurantDetail
import com.turk.core.domain.model.Restaurant
import com.turk.core.domain.model.RestaurantDetail
import com.turk.core.domain.network.ErrorEntity
import com.turk.core.domain.network.Result
import com.turk.core.domain.network.asResult
import com.turk.core.domain.network.getError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class RestaurantDataSourceImpl(private val apiService: HotelApiService) : RestaurantDataSource {

   private var cacheDataList: List<Restaurant> = arrayListOf()
    override fun getRestaurants(): Flow<Result<List<Restaurant>, ErrorEntity>> {

        return flow {
            emit(apiService.getRestaurants("3906535a-d96c-47cf-99b0-009fc9e038e0").asResult {
                cacheDataList =  it.data.map {
                    it.mapToRestaurant()
                }
                cacheDataList
            })
        }.onStart {
            emit(Result.Loading(isLoading = true))
        }.catch { ex ->
            emit(Result.Error(ex.getError()))
        }.onCompletion {
            emit(Result.Loading(isLoading = false))
        }
    }

    override fun getRestaurantDetailById(restaurantId: String): Flow<Result<RestaurantDetail, ErrorEntity>> {
        return flow {
            emit(apiService.getRestaurantDetailById(restaurantId).asResult {
                it.data.mapToRestaurantDetail(cacheDataList)
            })
        }.onStart {
            emit(Result.Loading(isLoading = true))
        }.catch { ex ->
            emit(Result.Error(ex.getError()))
        }.onCompletion {
            emit(Result.Loading(isLoading = false))
        }
    }
}

