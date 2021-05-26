package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo
import com.sano.skyengpet.domain.model.Translation

internal class DataDomainMapperImpl : DataDomainMapper {
    override fun wordSearchToTranslation(wordSearchPojo: WordSearchPojo): Translation? =
        wordSearchPojo.meanings?.let { meanings ->
            meanings.firstOrNull()?.let { mainMeaning ->
                mainMeaning.translation?.text?.let { transition ->
                    Translation(
                        translation = transition,
                        synonyms = meanings.mapNotNull { it.translation?.text },
                        imageUrl = mainMeaning.imageUrl
                    )
                }
            }
        }
}