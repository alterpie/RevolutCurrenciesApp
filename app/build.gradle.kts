plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}
android {
    compileSdkVersion(Libs.Android.compileSdkVersion)
    defaultConfig {
        applicationId = "com.example.revolutcurrenciesapp"
        minSdkVersion(Libs.Android.minSdkVersion)
        targetSdkVersion(Libs.Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = Libs.Test.testIntrumentationRunner
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

androidExtensions {
    isExperimental = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.Kotlin.stdlib)

    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Libs.Ui.appCompat)
    implementation(Libs.Ui.recyclerView)
    implementation(Libs.Ui.material)

    implementation(Libs.Koin.android)
    implementation(Libs.Koin.viewModel)

    implementation(Libs.OkHttp.loggingInterceptor)
    implementation(Libs.Moshi.core)
    implementation(Libs.Retrofit.moshiAdapter)

    implementation(Libs.Lifecycle.core)

    implementation(Libs.Kotlin.coroutines)
    implementation(Libs.Kotlin.coroutinesAndroid)

    testImplementation(Libs.Test.junit)
    androidTestImplementation(Libs.Test.espresso)
    androidTestImplementation(Libs.Test.testRunner)
}
