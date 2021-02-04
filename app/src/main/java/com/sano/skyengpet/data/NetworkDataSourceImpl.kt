package com.sano.skyengpet.data

import com.sano.skyengpet.BuildConfig
import com.sano.skyengpet.data.pojo.WordSearchPojo
import com.sano.skyengpet.data.service.SkyengService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class NetworkDataSourceImpl : NetworkDataSource {

    private val service: SkyengService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .apply {
                    service = create(SkyengService::class.java)
                }
    }

    override suspend fun searchWord(word: String): List<WordSearchPojo>? {
        return service.searchWord(word)
    }

}