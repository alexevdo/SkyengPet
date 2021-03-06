package com.sano.skyengpet.presentation.main

import com.sano.skyengpet.domain.model.Translation
import com.sano.skyengpet.presentation.ScreenState
import kotlinx.parcelize.Parcelize

sealed class MainViewScreenState : ScreenState {

    @Parcelize
    object Loading : MainViewScreenState()

    @Parcelize
    data class Translated(
            val searchWord: String,
            val translation: String,
            val translatedWords: List<String>? = null
    ) : MainViewScreenState()

    @Parcelize
    object NotFound : MainViewScreenState()

    @Parcelize
    data class Error(val error: Throwable) : MainViewScreenState()

}

