package com.example.presentation.screens.roomscreen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import com.example.presentation.DefaultTestRules
import com.example.presentation.R
import com.example.presentation.screens.retrofitscreen.entity.CatUi
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@HiltAndroidTest
class RoomScreenTest : DefaultTestRules() {
    private val viewModel = Mockito.mock(RoomViewModel::class.java)

    @Before
    fun setup() {
        composeTestRule.mainClock.autoAdvance = false
        // Disable Lottie animation
        // (if you want to test Lottie use composeTestRule.mainClock.advanceTimeBy($milliseconds))
    }

    @Test
    fun imagesDisplayed() {
        // GIVEN
        val images = listOf(
            CatUi(id = "1", image = "1", isFavorite = true),
            CatUi(id = "2", image = "2", isFavorite = true)
        )
        val state = MutableStateFlow(RoomContracts.State(images = images, isLoading = false))
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { RoomScreen(viewModel = viewModel) }
        // THEN
        composeTestRule
            .onNodeWithTag("List images")
            .onChildren()
            .assertCountEquals(images.size)
        images.forEach {
            composeTestRule
                .onNodeWithTag("List item: ${it.id}")
                .assertIsDisplayed()
        }
    }

    @Test
    fun favoritesImagesDisplayed() {
        // GIVEN
        val images = listOf(
            CatUi(id = "1", image = "1", isFavorite = true),
            CatUi(id = "2", image = "2", isFavorite = true)
        )
        val state = MutableStateFlow(RoomContracts.State(images = images, isLoading = false))
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { RoomScreen(viewModel = viewModel) }
        // THEN
        images.forEach {
            val expectedIconRes = R.drawable.ic_favorite.toString()
            composeTestRule
                .onNodeWithTag("Favorite icon: ${it.id}")
                .assertValueEquals(expectedIconRes)
        }
    }

    @Test
    fun imagesIsEmpty() {
        // GIVEN
        val images = emptyList<CatUi>()
        val state = MutableStateFlow(RoomContracts.State(images = images, isLoading = false))
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { RoomScreen(viewModel = viewModel) }
        // THEN
        composeTestRule
            .onNodeWithTag("Favorites is empty")
            .assertIsDisplayed()
    }
}
