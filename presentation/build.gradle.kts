@file:Suppress("UnstableApiUsage")

import Constants.COMPILE_SDK
import Constants.JVM_TARGET
import Constants.KOTLIN_COMPILER_EXTENSION_VERSION
import Constants.MIN_SDK
import Constants.SOURCE_COMPATIBILITY
import Constants.TARGET_COMPATIBILITY

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.example.presentation"
    compileSdk = COMPILE_SDK

    buildFeatures {
        buildConfig = true
        compose = true
    }
    buildTypes {
        debug {
            buildConfigField(
                "String",
                "README",
                "\"https://github.com/forenmis/clean-arch-template/blob/main/README.md\""
            )
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
        }
        release {
            buildConfigField(
                "String",
                "README",
                "\"https://github.com/forenmis/clean-arch-template/blob/main/README.md\""
            )
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
        create("qa") {
            initWith(getByName("debug"))
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENSION_VERSION
    }

    defaultConfig {
        minSdk = MIN_SDK

        testInstrumentationRunner = "com.example.presentation.HiltTestRunner"
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
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
    debugImplementation(libs.ui.tooling)
    implementation(libs.tooling.preview)
    implementation(libs.material.icons.extended)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.coil.compose)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.accompanist.permissions)

    detektPlugins(libs.detekt.compose.rules)

    // test
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)

    androidTestImplementation(libs.android.test.compose)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.dexmaker.mockito.inline.extended)
    androidTestImplementation(libs.runner)
    androidTestImplementation(platform(libs.firebase.bom))
    androidTestImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.compiler)
}

detekt {
    allRules = false // activate all available (even unstable) rules.
    config.setFrom(files(file("detekt.yml")))
}
