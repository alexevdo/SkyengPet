package com.sano.skyengpet.domain

import com.sano.skyengpet.domain.model.Translation
import kotlinx.coroutines.flow.Flow

interface IMainInteractor {

    fun searchWord(searchWord: String): Flow<StateMessage<Translation>>
}