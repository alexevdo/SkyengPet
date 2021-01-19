package com.sano.skyengpet.domain

import com.sano.skyengpet.domain.model.Translation

interface ISkyengRepository {
    suspend fun searchWord(word: String): Translation?
}