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
    alias(libs.plugins.room)
    alias(libs.plugins.junit5)
}

android {
    namespace = "com.example.data"
    compileSdk = COMPILE_SDK

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
            buildConfigField("String", "DATABASE_NAME", "\"Cats\"")
            buildConfigField("int", "DATABASE_VERSION", "1")

            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
            buildConfigField("String", "DATABASE_NAME", "\"Cats\"")
            buildConfigField("int", "DATABASE_VERSION", "1")

            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
        create("qa") {
            initWith(getByName("debug"))
            buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
            buildConfigField("String", "DATABASE_NAME", "\"Cats\"")
            buildConfigField("int", "DATABASE_VERSION", "1")
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
        }
    }

    defaultConfig {
        minSdk = MIN_SDK
    }

    compileOptions {
        sourceCompatibility = SOURCE_COMPATIBILITY
        targetCompatibility = TARGET_COMPATIBILITY
    }

    kotlinOptions {
        jvmTarget = JVM_TARGET
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}


dependencies {
    implementation(project(":common"))

    implementation(libs.core.ktx)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //test
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
}
