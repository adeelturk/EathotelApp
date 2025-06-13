package com.turk.eatapphotels

import androidx.multidex.MultiDexApplication
import com.turk.core.di.initKoin
import com.turk.eatapphotels.navigation.navigationModule
import com.turk.restaurantslist.di.restaurantsListModule
import org.koin.android.ext.koin.androidContext

class MyApp : MultiDexApplication()  {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApp)
            modules(listOf(
                navigationModule,
                restaurantsListModule
            ))
        }
    }
}