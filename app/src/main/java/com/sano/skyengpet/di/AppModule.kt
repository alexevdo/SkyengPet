package com.sano.skyengpet.di

import com.sano.skyengpet.data.*
import com.sano.skyengpet.domain.MainInteractor
import com.sano.skyengpet.domain.MainInteractorImpl
import com.sano.skyengpet.domain.SkyengRepository
import com.sano.skyengpet.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataDomainMapper> { DataDomainMapperImpl() }
    single<NetworkDataSource> { NetworkDataSourceImpl() }
    single<SkyengRepository> { SkyengRepositoryImpl(get(), get()) }
    single<MainInteractor> { MainInteractorImpl(get()) }

    // view model
    viewModel { MainViewModel(get()) }
}