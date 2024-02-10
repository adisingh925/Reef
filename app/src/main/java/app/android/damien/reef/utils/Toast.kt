package app.android.damien.reef.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Toast {

    fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}