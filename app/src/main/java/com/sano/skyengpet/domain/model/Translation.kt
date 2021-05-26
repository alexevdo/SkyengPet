package com.sano.skyengpet.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
    val translation: String,
    val synonyms: List<String>,
    val imageUrl: String? = null
) : Parcelable