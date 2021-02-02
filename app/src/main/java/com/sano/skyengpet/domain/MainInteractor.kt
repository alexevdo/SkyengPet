package com.sano.skyengpet.domain

import android.util.Log
import com.sano.skyengpet.domain.model.Translation

class MainInteractor(private val skyengRepository: ISkyengRepository): IMainInteractor {

    override suspend fun searchWord(searchWord: String): Translation? {
        try {
            return skyengRepository.searchWord(searchWord)
        } catch (e: Exception) {
            Log.e(MainInteractor::class.simpleName, e.toString())
            throw e
        }
    }
}


