package com.turk.core.data.model

import com.google.gson.annotations.SerializedName


data class RestaurantDetailDto(
    @SerializedName("data") var data: ArrayList<RestaurantDataDto> = arrayListOf(),
)