package com.turk.core.data.model

import com.google.gson.annotations.SerializedName


data class MetaDto(

    @SerializedName("limit") var limit: Int? = null,
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("current_page") var currentPage: Int? = null

)