package com.sano.skyengpet.presentation.main

import com.sano.skyengpet.domain.MainInteractor
import com.sano.skyengpet.presentation.BaseViewModel
import kotlinx.coroutines.launch

internal class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<MainViewScreenState>() {

    override fun handleError(error: Throwable) = MainViewScreenState.Error(error)

    private val translatedWords: MutableList<String> = mutableListOf()

    fun process(intent: MainIntent) {
        when (intent) {
            is MainIntent.Translate -> {
                changeState(MainViewScreenState.Loading)
                coroutineScope.launch {
                    changeState(translateWord(intent.searchWord))
                }
            }
        }
    }

    private suspend fun translateWord(searchWord: String): MainViewScreenState =
        interactor.searchWord(searchWord)?.let { translation ->
            translatedWords += searchWord
            MainViewScreenState.Translated(
                searchWord,
                translation.translation,
                translatedWords
            )
        } ?: MainViewScreenState.NotFound
}

sealed class MainIntent {
    data class Translate(val searchWord: String) : MainIntent()
}