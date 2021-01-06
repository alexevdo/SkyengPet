package com.sano.skyengpet.data.service

import com.sano.skyengpet.data.pojo.WordSearchPojo
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyengService {

    @GET("/api/public/v1/words/search")
    suspend fun searchWord(@Query("search") word: String?): List<WordSearchPojo>?

}