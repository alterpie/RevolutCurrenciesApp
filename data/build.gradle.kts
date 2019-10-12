plugins{
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
android {
    compileSdkVersion(Libs.Android.compileSdkVersion)

    defaultConfig {
        minSdkVersion(Libs.Android.minSdkVersion)
        targetSdkVersion(Libs.Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Libs.Test.testIntrumentationRunner
    }

    buildTypes {

        getByName("debug") {
            buildConfigField("String", "API_APP_URL", "\"https://revolut.duckdns.org\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")

            buildConfigField("String", "API_APP_URL", "https://revolut.duckdns.org")
        }
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)

    implementation(project(":domain"))

    implementation(Libs.Retrofit.core)
    implementation(Libs.Moshi.core)
    kapt(Libs.Moshi.codegen)
}
