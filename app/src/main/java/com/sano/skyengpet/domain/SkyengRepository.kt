package com.sano.skyengpet.domain

import com.sano.skyengpet.domain.model.Translation

interface SkyengRepository {

    suspend fun searchWord(word: String): Translation?
}