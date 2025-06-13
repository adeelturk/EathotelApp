package com.turk.core.di

import com.google.gson.GsonBuilder
import com.turk.core.data.sources.HotelApiService
import com.turk.core.data.sources.RestaurantDataSource
import com.turk.core.data.sources.RestaurantDataSourceImpl
import com.turk.core.domain.repository.RestaurantRepository
import com.turk.core.data.repository.RestaurantRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

val networkModule = module {

    single { OkHttpClient.Builder() }

    single<Retrofit> {
        val logger = HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        }


        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.apply {

            baseUrl("https://api.eat-sandbox.co/consumer/v2/")
            addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().serializeNulls().create()
                )
            )
            addCallAdapterFactory(RxJava3CallAdapterFactory.create())

        }.also {
            val okHttpClientBuilder = get<OkHttpClient.Builder>()
            okHttpClientBuilder.addInterceptor(logger)
            it.client(okHttpClientBuilder.build())
            okHttpClientBuilder.readTimeout(60, TimeUnit.SECONDS)
            okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS).build()
        }
        retrofitBuilder.build()

    }

    single { get<Retrofit>().create(HotelApiService::class.java) }
    singleOf(::RestaurantDataSourceImpl).bind<RestaurantDataSource>()
    singleOf(::RestaurantRepositoryImpl).bind<RestaurantRepository>()

}