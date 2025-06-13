package com.turk.core.data.model

import com.google.gson.annotations.SerializedName


data class RestaurantDto(

    @SerializedName("data") var data: ArrayList<RestaurantDataDto> = arrayListOf(),
    @SerializedName("meta") var meta: MetaDto? = MetaDto(),
    @SerializedName("links") var links: LinksDto? = LinksDto()

)