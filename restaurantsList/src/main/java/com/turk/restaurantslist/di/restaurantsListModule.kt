package com.turk.restaurantslist.di

import com.turk.restaurantslist.domain.GetRestaurantsDetailsUseCase
import com.turk.restaurantslist.domain.GetRestaurantsListUseCase
import com.turk.restaurantslist.presentation.RestaurantViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val restaurantsListModule = module {

    factory { GetRestaurantsListUseCase(get()) }
    factory { GetRestaurantsDetailsUseCase(get()) }
    viewModelOf(::RestaurantViewModel)

}