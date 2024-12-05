package com.example.presentation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavDestination

fun templateComposeRule() = createAndroidComposeRule<HiltTestActivity>()

inline fun <reified T : Any> NavDestination.isRoute(): Boolean {
    val clazz = T::class.java
    val candidateClassName = clazz.canonicalName
    return route == candidateClassName
}
