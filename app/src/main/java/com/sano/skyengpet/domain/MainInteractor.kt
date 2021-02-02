package com.sano.skyengpet.domain

import com.sano.skyengpet.domain.model.Translation

interface MainInteractor {

    suspend fun searchWord(searchWord: String): Translation?

}