package com.sano.skyengpet.data

import com.sano.skyengpet.domain.model.Translation

class SkyengRepository(private val networkDataSource: NetworkDataSource) {

    suspend fun searchWord(word: String): Translation? {
        return networkDataSource.searchWord(word)?.first()?.toTranslation()
    }
}