package com.turk.core.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(
    @SerializedName("data") val data: RestaurantDataDto
)
