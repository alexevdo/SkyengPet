import org.gradle.api.JavaVersion

object Config {
    const val applicationId = "com.sano.skyengpet"
    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    const val jvmTarget = "1.8"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}