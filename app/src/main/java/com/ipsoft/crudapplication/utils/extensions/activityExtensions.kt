package com.ipsoft.crudapplication.utils.extensions

import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.changeColor(@ColorRes colorId: Int) {
    this.window?.let {
        it.apply {
            statusBarColor = ContextCompat.getColor(this.context, colorId)
        }
    }
}