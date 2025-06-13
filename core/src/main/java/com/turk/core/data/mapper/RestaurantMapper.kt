package com.turk.core.data.mapper

import com.turk.core.data.model.RestaurantDataDto
import com.turk.core.domain.model.Restaurant
import com.turk.core.domain.model.RestaurantDetail

fun RestaurantDataDto.mapToRestaurant(): Restaurant {
    return Restaurant(
        id = this.id,
        name = this.attributes.name,
        location = this.attributes.addressLine1,
        rating = this.attributes.ratingsAverage?.toFloatOrNull(),
        priceLevel = this.attributes.priceLevel,
        cuisine = this.attributes.cuisine,
        imageUrl = this.attributes.imageUrl
    )
}


fun RestaurantDataDto.mapToRestaurantDetail(cacheDataList: List<Restaurant>): RestaurantDetail {
    return RestaurantDetail(
        name = this.attributes.name,
        location = this.attributes.addressLine1,
        rating = this.attributes.ratingsAverage?.toFloatOrNull(),
        priceLevel = this.attributes.priceLevel,
        cuisineDetail = "${this.attributes.cuisine} ${this.attributes.establishmentType}",
        imageUrl = this.attributes.imageUrl,
        description = this.attributes.description,
        labels = this.attributes.labels,
        otherRestaurantOfSameCuisine = cacheDataList.filter { it.cuisine == this.attributes.cuisine },
        otherRestaurantInSameArea = cacheDataList.filter { it.location == this.attributes.addressLine1 }
    )
}




