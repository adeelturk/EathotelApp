package com.turk.eatapphotels.navigation

import com.turk.restaurantslist.presentation.navigation.RestaurantNavController
import org.koin.dsl.binds
import org.koin.dsl.module

val navigationModule = module {
    single { Navigator() } binds   arrayOf( RestaurantNavController::class )
}