package com.sano.skyengpet

import android.app.Application
import com.sano.skyengpet.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SkyengPetApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@SkyengPetApp)
            modules(appModule)
        }
    }

}