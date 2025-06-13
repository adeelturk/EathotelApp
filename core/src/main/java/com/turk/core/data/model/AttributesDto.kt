package com.turk.core.data.model

import com.google.gson.annotations.SerializedName


data class AttributesDto(

    @SerializedName("name") var name: String? = null,
    @SerializedName("price_level") var priceLevel: Int? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("menu_url") var menuUrl: String? = null,
    @SerializedName("require_booking_preference_enabled") var requireBookingPreferenceEnabled: Boolean? = null,
    @SerializedName("difficult") var difficult: Boolean? = null,
    @SerializedName("cuisine") var cuisine: String? = null,
    @SerializedName("image_url") var imageUrl: String? = null,
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("address_line_1") var addressLine1: String? = null,
    @SerializedName("ratings_average") var ratingsAverage: String? = null,
    @SerializedName("ratings_count") var ratingsCount: Int? = null,
    @SerializedName("labels") var labels: ArrayList<String> = arrayListOf(),
    @SerializedName("alcohol") var alcohol: Boolean? = null,
    @SerializedName("deal") var deal: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("establishment_type") var establishmentType: String? = null,
    @SerializedName("external_ratings_url") var externalRatingsUrl: String? = null,
    @SerializedName("image_urls") var imageUrls: ArrayList<String> = arrayListOf(),
    @SerializedName("neighborhood_name") var neighborhoodName: String? = null,
    @SerializedName("notice") var notice: String? = null,
    @SerializedName("operating_hours") var operatingHours: String? = null,
    @SerializedName("outdoor_seating") var outdoorSeating: Boolean? = null,
    @SerializedName("postal_code") var postalCode: String? = null,
    @SerializedName("province") var province: String? = null,
    @SerializedName("relationship_type") var relationshipType: String? = null,
    @SerializedName("reservation_notice_duration") var reservationNoticeDuration: Int? = null,
    @SerializedName("smoking") var smoking: Boolean? = null,
    @SerializedName("valet") var valet: Boolean? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("address_line_2") var addressLine2: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("custom_confirmation_comments") var customConfirmationComments: String? = null,
    @SerializedName("terms_and_conditions") var termsAndConditions: String? = null,
    @SerializedName("ratings_img") var ratingsImg: String? = null

)