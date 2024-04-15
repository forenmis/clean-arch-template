import Constants.COMPILE_SDK
import Constants.JVM_TARGET
import Constants.MIN_SDK
import Constants.SOURCE_COMPATIBILITY
import Constants.TARGET_COMPATIBILITY
import Constants.TARGET_SDK
import Constants.VERSION_CODE
import Constants.VERSION_NAME

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.example.cleanarchitectiretemplate"
    compileSdk = COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.cleanarchitecturetemplate"
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode = VERSION_CODE
        versionName = VERSION_NAME
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(project(":presentation"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
