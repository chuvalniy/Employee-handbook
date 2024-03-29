package com.example.core.ui

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.example.core.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout


inline fun SearchView.onQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            listener(p0.orEmpty())
            return true
        }
    })
}

inline fun TabLayout.onTabSelected(crossinline listener: (Int) -> Unit) {
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let { listener.invoke(it.position) }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}
fun Context.getSnackBar(
    view: View,
    messageString: String,
    anchorView: View? = null,
): Snackbar? {
    return if (view.isAttachedToWindow) {
        val snackBar = Snackbar.make(view, messageString, Snackbar.LENGTH_INDEFINITE)
            .setAnchorView(anchorView)
            .addCallback(object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                    sb?.anchorView = null
                }
            })
        snackBar.view.background = ContextCompat.getDrawable(this, R.drawable.background_loading_snackbar)

        snackBar.show()
        return snackBar
    } else null
}

fun Context.showSnackBar(
    view: View,
    messageString: String,
    anchorView: View? = null,
) {
    if (view.isAttachedToWindow) {
        val snackBar = Snackbar.make(view, messageString, Snackbar.LENGTH_LONG)
            .setAnchorView(anchorView)
            .addCallback(object : Snackbar.Callback() {
                override fun onShown(sb: Snackbar?) {
                    super.onShown(sb)
                    sb?.anchorView = null
                }
            })
        snackBar.view.background = ContextCompat.getDrawable(this, R.drawable.bg_error_snackbar)
        snackBar.show()
    }
}