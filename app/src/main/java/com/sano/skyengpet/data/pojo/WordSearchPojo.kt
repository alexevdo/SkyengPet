package com.sano.skyengpet.data.pojo

import com.google.gson.annotations.SerializedName

data class WordSearchPojo(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("meanings") var meanings: List<MeaningPojo>? = null
)