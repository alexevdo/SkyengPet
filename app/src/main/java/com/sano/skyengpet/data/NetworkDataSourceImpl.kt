package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo
import com.sano.skyengpet.data.service.SkyengService

internal class NetworkDataSourceImpl(private val service: SkyengService) : NetworkDataSource {

    override suspend fun searchWord(word: String): List<WordSearchPojo>? {
        return service.searchWord(word)
    }

}