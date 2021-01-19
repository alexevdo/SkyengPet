package com.sano.skyengpet.data.pojo

import com.google.gson.annotations.SerializedName

internal data class MeaningPojo(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("partOfSpeechCode") val partOfSpeechCode: String? = null,
    @SerializedName("translation") val translation: TranslationPojo? = null,
    @SerializedName("previewUrl") val previewUrl: String? = null,
    @SerializedName("imageUrl") val imageUrl: String? = null,
    @SerializedName("transcription") val transcription: String? = null,
    @SerializedName("soundUrl") val soundUrl: String? = null
)