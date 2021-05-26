package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo
import com.sano.skyengpet.domain.model.Translation

internal interface DataDomainMapper {
    fun wordSearchToTranslation(wordSearchPojo: WordSearchPojo): Translation?
}