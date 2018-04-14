package com.jxhem.uicentric.ui.views.base

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View


open class BaseFragment : Fragment() {

    fun showMessage(view: View, message: String) {
        val snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}
