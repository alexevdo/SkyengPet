object Versions {
    // Design
    const val appcompat = "1.2.0"
    const val material = "1.2.1"
    const val constraintLayout = "2.0.4"

    // Ktx
    const val coreKtx = "1.3.2"
    const val fragmentKtx = "1.2.5"
    const val lifecycleKtx = "2.2.0"

    // Coroutines
    const val kotlinXCoroutinesAndroid = "1.3.9"

    // Testing
    const val jUnit = "4.13.1"
    const val extJUnit = "1.1.2"
    const val espressoCore = "3.3.0"

    // Retrofit
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"

    // OkHttp
    const val loggingInterceptor = "4.2.1"

}

object Design {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Ktx {
    const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}

object Coroutines {
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinXCoroutinesAndroid}"
}

object Testing {
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val extJUnit = "androidx.test.ext:junit:${Versions.extJUnit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
}

object OkHttp {
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
}