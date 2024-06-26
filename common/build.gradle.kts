import Constants.COMPILE_SDK
import Constants.JVM_TARGET
import Constants.MIN_SDK
import Constants.SOURCE_COMPATIBILITY
import Constants.TARGET_COMPATIBILITY

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}
android {
    namespace = "com.example.common"
    compileSdk = COMPILE_SDK

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = MIN_SDK
    }

    buildTypes {
        debug {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
        }
        release {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
        create("qa") {
            initWith(getByName("debug"))
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
    }

    compileOptions {
        sourceCompatibility = SOURCE_COMPATIBILITY
        targetCompatibility = TARGET_COMPATIBILITY
    }

    kotlinOptions {
        jvmTarget = JVM_TARGET
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.timber)
}
