package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo

internal interface NetworkDataSource {
    suspend fun searchWord(word: String): List<WordSearchPojo>?
}