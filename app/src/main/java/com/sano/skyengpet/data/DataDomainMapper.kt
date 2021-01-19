package com.sano.skyengpet.data

import com.sano.skyengpet.data.pojo.WordSearchPojo
import com.sano.skyengpet.domain.model.Translation

internal fun WordSearchPojo.toTranslation(): Translation {
    return Translation(meanings?.first()?.translation?.text)
}