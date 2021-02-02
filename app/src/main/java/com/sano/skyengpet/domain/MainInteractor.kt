package com.sano.skyengpet.domain

import android.util.Log
import com.sano.skyengpet.data.SkyengRepository
import com.sano.skyengpet.domain.StateMessage.Companion.emptyResult
import com.sano.skyengpet.domain.StateMessage.Companion.fail
import com.sano.skyengpet.domain.StateMessage.Companion.requestStart
import com.sano.skyengpet.domain.StateMessage.Companion.success
import com.sano.skyengpet.domain.model.Translation
import kotlinx.coroutines.flow.flow

class MainInteractor(private val skyengRepository: ISkyengRepository): IMainInteractor {

    override fun searchWord(searchWord: String) = flow<StateMessage<Translation>> {
        emit(searchWordIntentId.requestStart(Source.NETWORK))
        Log.d(MainInteractor.javaClass.simpleName, "Test")
        try {
            skyengRepository.searchWord(searchWord)?.let {
                emit(searchWordIntentId.success(Source.NETWORK, it))
            } ?: emit(searchWordIntentId.emptyResult(Source.NETWORK))
        } catch (e: Exception) {
            Log.e(MainInteractor.javaClass.simpleName, e.toString())
            emit(searchWordIntentId.fail(source = Source.NETWORK, 101))
        }

    }

    companion object {
        val searchWordIntentId = IntentId("SEARCH_WORD_INTENT_ID")
    }

}


