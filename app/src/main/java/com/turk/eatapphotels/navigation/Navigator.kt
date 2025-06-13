package com.turk.eatapphotels.navigation

import android.content.Context
import com.turk.restaurantslist.presentation.navigation.RestaurantNavController

class Navigator : RestaurantNavController {

    private var navController: MainNavController?=null

    fun setNavigationController(navController: MainNavController){
        this.navController=navController
    }

    override fun showRestaurantDetail(restaurantId: String) {
        navController?.showRestaurantDetail(restaurantId)
    }
}