package com.sano.skyengpet.domain

import com.sano.skyengpet.domain.model.Translation

class MainInteractorImpl(private val skyengRepository: SkyengRepository) : MainInteractor {

    override suspend fun searchWord(searchWord: String): Translation? =
        skyengRepository.searchWord(searchWord)


}


