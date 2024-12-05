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
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest : DefaultTestRules() {
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.mainClock.autoAdvance = false
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            HomeScreen(bottomBarNavController = navController, onNavigateToPlayer = {})
        }
    }

    @Test
    fun verifyBottomBarItems() = runTest {
        BottomRoute.all().forEach { screen ->
            composeTestRule
                .onNodeWithTag(screen.screenName)
                .assertIsDisplayed()
                .performClick()
        }
    }
}
