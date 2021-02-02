package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo
import com.sano.skyengpet.domain.model.Translation

internal class DataDomainMapperImpl : DataDomainMapper {
    override fun wordSearchToTranslation(wordSearchPojo: WordSearchPojo): Translation {
        return Translation(wordSearchPojo.meanings?.first()?.translation?.text)
    }
}