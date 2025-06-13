package com.turk.core.extensions

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.turk.core.R
import com.turk.core.domain.network.ErrorEntity


fun Fragment.showSnackBarMsg(view:View, msg: String) {
    try {
        val snackBar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
        val sbView: View = snackBar.view
        sbView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.red));
        ViewCompat.setBackgroundTintList(
            sbView,
            ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.red))
        )
        (sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text))?.apply {
            setTextColor(Color.WHITE)
            setTypeface(null, Typeface.BOLD)
        }
        snackBar.show()
    } catch (_: Exception) {

    }
}


fun Fragment.getFailureMessage(errorEntity: ErrorEntity?):String{

   return when (errorEntity) {
        is ErrorEntity.AuthError -> 
            getString(R.string.failure_authorization)

        is ErrorEntity.Forbidden -> getString(R.string.failure_forbidden)
        is ErrorEntity.InternalServerError -> 
            getString(R.string.failure_forbidden)

        is ErrorEntity.BadRequest -> getString(R.string.failure_bad_request)
        is ErrorEntity.NotFound -> getString(R.string.failure_not_found)
        is ErrorEntity.AndroidError -> getString(R.string.failure_android_error)
        is ErrorEntity.UnSupportedMediaType -> getString(R.string.failure_unsupportedmedia)
        is ErrorEntity.MalFormedJson -> getString(R.string.failure_malformedJson)
        is ErrorEntity.JsonSyntaxException -> getString(R.string.failure_malformedJson)
        is ErrorEntity.ServerError -> getString(R.string.failure_server_error)
        is ErrorEntity.NetworkConnection -> getString(R.string.failure_network_connection)
        else -> getString(R.string.something_went_wrong)
    }

}