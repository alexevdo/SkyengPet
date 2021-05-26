package com.sano.skyengpet.presentation

import android.content.Context
import android.widget.Toast
import androidx.annotation.IntDef
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.sano.skyengpet.R

val <T> T.exhaustive: T
    get() = this

fun <T> Fragment.observe(liveData: LiveData<T>, observer: T.() -> Unit ) =
    liveData.observe(viewLifecycleOwner, { observer(it) })

fun Fragment.toast(@StringRes stringRes: Int) {
    Toast.makeText(context, getString(stringRes), Toast.LENGTH_LONG).show()
}