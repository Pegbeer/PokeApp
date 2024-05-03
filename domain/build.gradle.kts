plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.pegbeer.pokeapp.domain"
}

kotlin{
    jvmToolchain(17)
}

dependencies{

    implementation(projects.core)
    implementation(projects.data)
    implementation(libs.bundles.retrofit)
    implementation(libs.kotlinx.coroutines)
}