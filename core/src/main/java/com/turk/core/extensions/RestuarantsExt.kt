package com.turk.core.extensions

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.turk.core.R

@BindingAdapter("priceLevel", requireAll = true)
fun TextView.setPriceLeve(priceLevel:Int){
    text=priceLevel.toPriceLevelSign(context)
}

fun Int.toPriceLevelSign(context: Context):String{
    val priceLevelSign=context.getString(R.string.dollar_sign)
    var priceLevel=""
    for (i in 0.. this){
        priceLevel+=priceLevelSign
    }
    return priceLevel
}

@BindingAdapter("cuisineTitle", requireAll = true)
fun TextView.setCuisineTitle(cusisine:String?){
    text="${context.getString(R.string.other_venues)} $cusisine"
}

@BindingAdapter("locationTitle", requireAll = true)
fun TextView.setLocationTitle(location:String?){
    text="${context.getString(R.string.other_venues_in)} $location"
}