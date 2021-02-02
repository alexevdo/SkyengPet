package com.sano.skyengpet.domain

import android.util.Log
import com.sano.skyengpet.domain.model.Translation

class MainInteractorImpl(private val skyengRepository: SkyengRepository) : MainInteractor {

    override suspend fun searchWord(searchWord: String): Translation? {
        try {
            return skyengRepository.searchWord(searchWord)
        } catch (e: Exception) {
            Log.e(MainInteractorImpl::class.simpleName, e.toString())
            throw e
        }
    }
}


