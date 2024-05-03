plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.symbolProcessing)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.pegbeer.pokeapp.data"
}

kotlin{
    jvmToolchain(17)
}

dependencies {

    implementation(projects.core)
    implementation(projects.domain)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.room)

    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)

    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
}