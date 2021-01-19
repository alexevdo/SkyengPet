package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo

interface INetworkDataSource {
    suspend fun searchWord(word: String): List<WordSearchPojo>?
}