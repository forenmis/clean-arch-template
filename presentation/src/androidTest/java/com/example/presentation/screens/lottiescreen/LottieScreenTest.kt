package com.example.presentation.screens.lottiescreen

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.presentation.DefaultTestRules
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
class LottieScreenTest : DefaultTestRules() {
    private val viewModel = mock(LottieViewModel::class.java)

    @Before
    fun setup() {
        composeTestRule.setContent { LottieScreen(viewModel = viewModel) }
    }

    @Test
    fun crashButtonIsDisplayed() = runTest {
        composeTestRule.awaitIdle()
        composeTestRule
            .onNodeWithText("Crash")
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}
