package com.example.presentation

import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Rule

@Suppress("LeakingThis")
abstract class DefaultTestRules internal constructor() {
    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = templateComposeRule()
}
