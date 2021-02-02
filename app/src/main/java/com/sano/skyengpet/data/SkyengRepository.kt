package com.sano.skyengpet.data

import com.sano.skyengpet.domain.ISkyengRepository
import com.sano.skyengpet.domain.model.Translation

internal class SkyengRepository(private val networkDataSource: NetworkDataSource, private val dataDomainMapper: DataDomainMapper): ISkyengRepository {

    override suspend fun searchWord(word: String): Translation? {
        return networkDataSource.searchWord(word)?.first()?.let {
            dataDomainMapper.wordSearchToTranslation(it)
        }
    }
}