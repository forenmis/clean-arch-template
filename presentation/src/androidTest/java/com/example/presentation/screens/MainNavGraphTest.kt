package com.example.presentation.screens

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.presentation.DefaultTestRules
import com.example.presentation.isRoute
import com.example.presentation.utils.Home
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class MainNavGraphTest : DefaultTestRules() {

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MainNavGraph(navController = navController)
        }
    }

    @Test
    fun verifyStartDestination() {
        val expected = true
        val actual = navController.currentBackStackEntry?.destination?.isRoute<Home>()
        assertEquals(expected, actual)
    }
}
