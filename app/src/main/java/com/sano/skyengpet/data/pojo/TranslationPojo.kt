package com.sano.skyengpet.data.pojo

import com.google.gson.annotations.SerializedName

internal data class TranslationPojo(
        @SerializedName("text") val text: String? = null,
        @SerializedName("note") val note: String? = null
)