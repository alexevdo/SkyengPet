package com.sano.skyengpet.presentation.state

import com.sano.skyengpet.domain.model.Translation
import kotlinx.android.parcel.Parcelize

sealed class MainViewScreenState : ScreenState {
    @Parcelize
    object Loading : MainViewScreenState()

    @Parcelize
    data class Translated(
        val searchWord: String? = null,
        val translation: Translation,
        val translatedWords: List<String>? = null
    ) : MainViewScreenState()

    @Parcelize
    object NotFound : MainViewScreenState()

    @Parcelize
    data class Error(val error: Throwable) : MainViewScreenState()
}

