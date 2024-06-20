package com.example.presentation.screens.lottiescreen

import com.example.presentation.DefaultTestRules
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.mockito.Mockito.mock

@HiltAndroidTest
class LottieScreenTest : DefaultTestRules() {
    private val viewModel = mock(LottieViewModel::class.java)

    @Before
    fun setup() {
        composeTestRule.setContent { LottieScreen(viewModel = viewModel) }
    }
}
