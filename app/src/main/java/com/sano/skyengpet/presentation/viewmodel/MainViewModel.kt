package com.sano.skyengpet.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sano.skyengpet.data.NetworkDataSource
import com.sano.skyengpet.data.SkyengRepository
import com.sano.skyengpet.domain.IMainInteractor
import com.sano.skyengpet.domain.MainInteractor
import com.sano.skyengpet.domain.ViewStateMessage
import com.sano.skyengpet.presentation.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class MainViewModel : ViewModel() {

    private val interactor: IMainInteractor = MainInteractor(SkyengRepository(NetworkDataSource()))

    private val mutableViewState: MutableLiveData<MainViewState> = MutableLiveData()
    val viewState = mutableViewState as LiveData<MainViewState>

    private val mutableStateMessage: MutableLiveData<ViewStateMessage> = MutableLiveData()
    val stateMessage = mutableStateMessage as LiveData<ViewStateMessage>

    private val mutableTranslatedWords: MutableList<String> = mutableListOf()

    private var mergedViewState: MainViewState = MainViewState()
        set(value) {
            var viewState: MainViewState = field

            value.translation?.let { viewState = viewState.copy(translation = it) }
            value.searchWord?.let { viewState = viewState.copy(searchWord = it) }
            value.translatedWords?.let { viewState = viewState.copy(translatedWords = it) }

            if(field != viewState) {
                mutableViewState.value = viewState
            }
            field = viewState
        }

    fun process(intent: MainIntent) {
        when (intent) {
            is MainIntent.Translate -> {
                interactor.searchWord(intent.searchWord)
                    .onEach {
                        mutableStateMessage.value = it.toViewStateMessage()
                        if (it.hasData) {
                            mutableTranslatedWords += intent.searchWord
                            mergedViewState = MainViewState(
                                translation = it.data, translatedWords = mutableTranslatedWords, searchWord = intent.searchWord
                            )
                        }
                    }
                    .flowOn(Dispatchers.Main)
                    .launchIn(viewModelScope)
            }
        }
    }
}

sealed class MainIntent {
    data class Translate(val searchWord: String) : MainIntent()
}