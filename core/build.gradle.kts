plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "me.pegbeer.pokeapp.core"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

kotlin{
    jvmToolchain(17)
}

dependencies{
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.palette)

    implementation(platform(libs.compose.bom))
    implementation(libs.serialization.json)
    implementation(libs.paging.common)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.compose.animation)

    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    debugImplementation(libs.ui.tooling)
}