package com.sano.skyengpet.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sano.skyengpet.presentation.main.MainViewScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel<T : ScreenState> : ViewModel() {

    private val _screenState: MutableLiveData<T> = MutableLiveData()

    val screenState: LiveData<T>
        get() = _screenState

    val coroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            Log.e("ERROR", null, throwable)
            handleError(throwable)?.let { errorState -> changeState(errorState) }
        })

    protected fun changeState(newState: T) {
        _screenState.value = newState
    }

    open fun handleError(error: Throwable): T? = null
}