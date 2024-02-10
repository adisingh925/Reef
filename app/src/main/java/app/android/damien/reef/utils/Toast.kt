package app.android.damien.reef.utils

import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

object Toast {

    fun showSnackbar(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, 200)
        snackbarView.layoutParams = params
        snackbar.show()
    }
}