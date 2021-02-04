package com.sano.skyengpet.data

import com.sano.skyengpet.domain.SkyengRepository
import com.sano.skyengpet.domain.model.Translation

internal class SkyengRepositoryImpl(
        private val networkDataSource: NetworkDataSource,
        private val dataDomainMapper: DataDomainMapper
) : SkyengRepository {

    override suspend fun searchWord(word: String): Translation? {
        return networkDataSource.searchWord(word)?.first()?.let {
            dataDomainMapper.wordSearchToTranslation(it)
        }
    }
}