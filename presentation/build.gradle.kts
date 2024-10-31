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
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.example.presentation"
    compileSdk = COMPILE_SDK

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }
    buildTypes {
        debug {
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
            buildConfigField(
                "String",
                "README",
                "\"https://github.com/forenmis/clean-arch-template/blob/main/README.md\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_VIDEO_URI",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_1",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_2",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_3",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_4",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_5",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_6",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_8",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_9",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_10",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4\""
            )
        }
        release {
            buildConfigField(
                "String",
                "README",
                "\"https://github.com/forenmis/clean-arch-template/blob/main/README.md\""
            )
            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_7",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_5",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4\""
            )

            buildConfigField(
                "String",
                "EXAMPLE_TEST_VIDEO_URI_6",
                "\"https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4\""
            )

            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
        create("qa") {
            initWith(getByName("debug"))
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = KOTLIN_COMPILER_EXTENSION_VERSION
    }

    defaultConfig {
        minSdk = MIN_SDK

        testInstrumentationRunner = "com.example.presentation.HiltTestRunner"

        buildConfigField(
            "String",
            "BANNER_AD_UNIT_ID",
            "\"ca-app-pub-4286207698894720/5710469862\""
        )
        buildConfigField(
            "String",
            "INTERSTITIAL_AD_UNIT_ID",
            "\"ca-app-pub-4286207698894720/2542380463\""
        )
        buildConfigField(
            "String",
            "NATIVE_AD_UNIT_ID",
            "\"ca-app-pub-4286207698894720/2574740876\""
        )
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
    implementation(libs.splashscreen)

    implementation(libs.compose.material3)
    implementation(libs.lottie)
    implementation(libs.lottie.compose)
    implementation(libs.ui.viewbinding)
    implementation(platform(libs.compose.bom))
    debugImplementation(libs.ui.tooling)
    implementation(libs.tooling.preview)
    implementation(libs.material.icons.extended)

    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.coil.compose)

    // lottie
    implementation(libs.lottie)
    implementation(libs.lottie.compose)

    // DI
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // media player
    implementation(libs.media3.ui)
    implementation(libs.media3.exoplayer)

    // detekt
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.firebase.auth)

    implementation(libs.accompanist.permissions)

    implementation(libs.play.services.ads)

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
