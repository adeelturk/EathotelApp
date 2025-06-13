package com.turk.core.data.model

import com.google.gson.annotations.SerializedName


data class LinksDto(

    @SerializedName("first") var first: String? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("prev") var prev: String? = null,
    @SerializedName("last") var last: String? = null

)