pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.settings") version "8.4.0"
}

android {
    minSdk = 23
    compileSdk = 34
}

rootProject.name = "PokeApp"
include(":app")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":data")
include(":core")
