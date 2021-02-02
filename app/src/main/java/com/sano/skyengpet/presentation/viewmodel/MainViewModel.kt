package com.sano.skyengpet.presentation.viewmodel

import com.sano.skyengpet.domain.MainInteractor
import com.sano.skyengpet.presentation.state.MainViewScreenState
import kotlinx.coroutines.*

internal class MainViewModel(private val interactor: MainInteractor) :
    StateFullBaseViewModel<MainViewScreenState>() {

    private val translatedWords: MutableList<String> = mutableListOf()

    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            changeState(MainViewScreenState.Error(throwable))
        })

    fun process(intent: MainIntent) {
        when (intent) {
            is MainIntent.Translate -> {
                changeState(MainViewScreenState.Loading)
                viewModelCoroutineScope.launch {
                    val translation = interactor.searchWord(intent.searchWord)

                    if (translation == null) {
                        changeState(MainViewScreenState.NotFound)
                    } else {
                        translatedWords += intent.searchWord
                        changeState(
                            MainViewScreenState.Translated(
                                intent.searchWord,
                                translation,
                                translatedWords
                            )
                        )
                    }
                }
            }
        }
    }
}

sealed class MainIntent {
    data class Translate(val searchWord: String) : MainIntent()
}