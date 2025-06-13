package com.turk.eatapphotels

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.turk.eatapphotels.navigation.MainNavController
import com.turk.eatapphotels.navigation.Navigator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() ,MainNavController{

    private val navigator: Navigator by inject()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        navigator.setNavigationController(this)
    }


    override fun showRestaurantDetail(restaurantId: String) {
        val bundle = Bundle().apply {
            putString("restaurantId",restaurantId)
        }
        navController.navigate(R.id.action_restaurantsListFragment_to_restaurantsDetailFragment,bundle)
    }
}