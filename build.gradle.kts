import Constants.JVM_TARGET
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.room) apply false
    alias(libs.plugins.junit5) apply false
    alias(libs.plugins.navigationSafeArgs) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinSerialization) apply false
}

//ktlint
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
        ignoreFailures.set(false)
        filter {
            exclude("**/PreviewParams.kt")
        }
    }
}

//detekt
tasks.withType<Detekt>().configureEach {
    jvmTarget = JVM_TARGET
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = JVM_TARGET
}
