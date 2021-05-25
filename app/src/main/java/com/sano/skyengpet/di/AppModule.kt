package com.sano.skyengpet.di

import com.sano.skyengpet.BuildConfig
import com.sano.skyengpet.data.*
import com.sano.skyengpet.data.service.SkyengService
import com.sano.skyengpet.domain.MainInteractor
import com.sano.skyengpet.domain.MainInteractorImpl
import com.sano.skyengpet.domain.SkyengRepository
import com.sano.skyengpet.presentation.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideSkyengService(get()) }

    single<DataDomainMapper> { DataDomainMapperImpl() }
    single<NetworkDataSource> { NetworkDataSourceImpl(get()) }
    single<SkyengRepository> { SkyengRepositoryImpl(get(), get()) }
    single<MainInteractor> { MainInteractorImpl(get()) }

    // view model
    viewModel { MainViewModel(get()) }
}

internal fun provideSkyengService(retrofit: Retrofit): SkyengService {
    return retrofit.create(SkyengService::class.java)
}

internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}

internal fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}