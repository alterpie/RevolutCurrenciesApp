object Libs {

    object BuildPlugins {
        private const val androidGradlePluginVersion = "3.5.1"
        const val androidGradlePlugin = "com.android.tools.build:gradle:$androidGradlePluginVersion"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }

    object Android {
        const val compileSdkVersion = 29
        const val minSdkVersion = 16
        const val targetSdkVersion = compileSdkVersion
    }

    object Kotlin {
        internal const val version = "1.3.50"
        private const val coroutinesVersion = "1.3.2"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    }

    object Ui {
        private const val appCompatVersion = "1.1.0"
        private const val recyclerViewVersion = "1.0.0"
        private const val materialVersion = "1.0.0"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        const val material = "com.google.android.material:material:$materialVersion"
    }

    object Lifecycle {
        private const val version = "2.1.0"
        const val core = "androidx.lifecycle:lifecycle-extensions:$version"
    }

    object Koin {
        private const val version = "2.0.1"
        const val android = "org.koin:koin-android:$version"
        const val viewModel = "org.koin:koin-android-viewmodel:$version"
    }

    object Moshi {
        private const val version = "1.8.0"
        const val core = "com.squareup.moshi:moshi:$version"
        const val codegen = "com.squareup.moshi:moshi-kotlin-codegen:$version"
    }

    object Retrofit {
        private const val version = "2.6.2"
        const val moshiAdapter = "com.squareup.retrofit2:converter-moshi:$version"
        const val core = "com.squareup.retrofit2:retrofit:$version"
    }

    object OkHttp {
        private const val version = "3.12.1"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Test {
        private const val espressoVersion = "3.1.1"
        private const val testRunnerVersion = "1.1.1"
        private const val jUnitVersion = "4.12"

        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val testRunner = "androidx.test:runner:$testRunnerVersion"
        const val junit = "junit:junit:$jUnitVersion"
        const val testIntrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
