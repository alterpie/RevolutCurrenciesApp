plugins {
    id("java-library")
    kotlin("jvm")
}
dependencies {
    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.Kotlin.coroutines)
}
