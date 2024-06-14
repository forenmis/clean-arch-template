package com.example.presentation.screens.lottiescreen

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.example.presentation.templateComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class LottieScreenTest {
    private val viewModel = mock(LottieViewModel::class.java)

    @get:Rule
    val composeTestRule = templateComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent { LottieScreen(viewModel = viewModel) }
    }

    @Test
    fun crashButtonIsDisplayed() {
        composeTestRule
            .onNodeWithText("Crash")
            .assertIsDisplayed()
            .assertHasClickAction()
    }
}
