package com.sano.skyengpet.domain

class StateMessage<T>(
    val data: T? = null,
    val intentId: IntentId,
    val source: Source? = Source.NONE,
    val event: Event = Event.OTHER,
    val isProgressIndicator: Boolean = false,
    val errorCode: Int? = null
) {

    val hasData
        get() = event == Event.RESULT_SUCCESS && data != null

    fun toViewStateMessage() =
        ViewStateMessage(intentId, source, event, isProgressIndicator, errorCode)

    companion object {
        fun <T> IntentId.fail(
            source: Source,
            errorCode: Int,
            isProgressIndicator: Boolean = false
        ) =
            StateMessage<T>(
                source = source,
                intentId = this,
                event = Event.RESULT_FAIL,
                errorCode = errorCode
            )

        fun <T> IntentId.success(source: Source, data: T, isProgressIndicator: Boolean = false) =
            StateMessage(
                data = data,
                intentId = this,
                source = source,
                event = Event.RESULT_SUCCESS
            )

        fun <T> IntentId.requestStart(source: Source, isProgressIndicator: Boolean = true) =
            StateMessage<T>(intentId = this, source = source, event = Event.REQUEST_START)

        fun <T> IntentId.emptyResult(source: Source, isProgressIndicator: Boolean = false) =
            StateMessage<T>(intentId = this, source = source, event = Event.RESULT_EMPTY)
    }
}

class ViewStateMessage(
    val intentId: IntentId,
    val source: Source? = Source.NONE,
    val event: Event = Event.OTHER,
    val isProgressIndicator: Boolean = false,
    val errorCode: Int? = null
) {
    val isFailed
        get() = event == Event.RESULT_FAIL
}

class IntentId(val id: String)

enum class Source {
    NETWORK,
    LOCAL,
    NONE
}

enum class Event {
    REQUEST_START,
    RESULT_SUCCESS,
    REQUEST_CANCELED,
    RESULT_FAIL,
    RESULT_EMPTY,
    OTHER
}