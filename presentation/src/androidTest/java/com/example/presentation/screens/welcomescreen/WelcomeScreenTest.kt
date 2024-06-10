package com.example.presentation.screens.welcomescreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class WelcomeScreenTest {
    private val viewModel = Mockito.mock(WelcomeViewModel::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.mainClock.autoAdvance = false
        // Disable Lottie animation
        // (if you want to test Lottie use composeTestRule.mainClock.advanceTimeBy($milliseconds))
        composeTestRule.setContent { WelcomeScreen(viewModel = viewModel) }
    }

    @Test
    fun welcomeImageIsDisplayed() {
        composeTestRule
            .onNodeWithTag("Welcome image")
            .assertIsDisplayed()
    }

    @Test
    fun welcomeTextIsDisplayed() {
        composeTestRule
            .onNodeWithTag("Welcome text")
            .assertIsDisplayed()
        for (i in 1..3) {
            composeTestRule
                .onNodeWithTag("Welcome text part $i")
                .assertIsDisplayed()
        }
    }

    @Test
    fun catImagesIsDisplayed() {
        for (i in 1..2) {
            composeTestRule
                .onNodeWithTag("Image cat $i")
                .assertIsDisplayed()
        }
    }

    @Test
    fun linkIsClickable() {
        composeTestRule
            .onNodeWithTag("Welcome link")
            .assertIsDisplayed()
            .assertIsEnabled()
    }
}
