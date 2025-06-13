package com.turk.core.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantDataDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("attributes") var attributes: AttributesDto = AttributesDto(),
    )