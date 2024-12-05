package com.example.presentation.screens.welcomescreen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import com.example.presentation.DefaultTestRules
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@HiltAndroidTest
class WelcomeScreenTest : DefaultTestRules() {
    private val viewModel = Mockito.mock(WelcomeViewModel::class.java)

    @Before
    fun setup() {
        composeTestRule.mainClock.autoAdvance = false
        val effect = MutableSharedFlow<WelcomeContracts.Effect>()
        whenever(viewModel.effect).thenReturn(effect)
        // Disable Lottie animation
        // (if you want to test Lottie use composeTestRule.mainClock.advanceTimeBy($milliseconds))
    }

    @Test
    fun welcomeImageIsDisplayed() {
        // GIVEN
        val state = MutableStateFlow(WelcomeContracts.State())
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { WelcomeScreen(viewModel = viewModel) }
        // THEN
        composeTestRule
            .onNodeWithTag("Welcome image")
            .assertIsDisplayed()
    }

    @Test
    fun welcomeTextIsDisplayed() {
        // GIVEN
        val state = MutableStateFlow(WelcomeContracts.State())
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { WelcomeScreen(viewModel = viewModel) }
        // THEN
        composeTestRule
            .onNodeWithTag("Welcome text")
            .assertIsDisplayed()
        for (i in 1..3) {
            composeTestRule
                .onNodeWithTag("Welcome text part $i")
                .assertIsDisplayed()
        }
    }
}
