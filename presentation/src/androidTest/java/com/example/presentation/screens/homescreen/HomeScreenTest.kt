package com.example.presentation.screens.homescreen

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.presentation.DefaultTestRules
import com.example.presentation.utils.BottomRoute
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
class HomeScreenTest : DefaultTestRules() {
    private lateinit var navController: TestNavHostController
    private val viewModel = mock(HomeViewModel::class.java)

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            HomeScreen(
                viewModel = viewModel
            )
        }
    }

    @Test
    fun verifyStartDestination() {
        val expected = BottomRoute.Welcome.route
        val actual = navController.currentBackStackEntry?.destination?.route
        assertEquals(expected, actual)
    }

    @Test
    fun verifyBottomRoutes() {
        BottomRoute.all().forEach {
            composeTestRule
                .onNodeWithTag(it.route)
                .assertIsDisplayed()
                .performClick()
            val expected = it.route
            val actual = navController.currentBackStackEntry?.destination?.route
            assertEquals(expected, actual)
        }
    }
}
