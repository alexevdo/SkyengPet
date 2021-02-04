package com.sano.skyengpet.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sano.skyengpet.presentation.state.ScreenState

abstract class StateFullBaseViewModel<T : ScreenState> : ViewModel() {
    private val _screenState: MutableLiveData<T> = MutableLiveData()

    protected fun changeState(newState: T) {
        _screenState.value = newState
    }

    fun subscribeToScreenStateChanges(): LiveData<T> = _screenState
}