@file:Suppress("IllegalIdentifier")

package com.example.presentation.screens.retrofiscreen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertValueEquals
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import com.example.presentation.DefaultTestRules
import com.example.presentation.R
import com.example.presentation.screens.retrofitscreen.RetrofitContracts
import com.example.presentation.screens.retrofitscreen.RetrofitScreen
import com.example.presentation.screens.retrofitscreen.RetrofitViewModel
import com.example.presentation.screens.retrofitscreen.entity.CatUi
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@HiltAndroidTest
class RetrofitScreenTest : DefaultTestRules() {
    private val viewModel = Mockito.mock(RetrofitViewModel::class.java)

    @Test
    fun imagesDisplayed() {
        // GIVEN
        val images = listOf(
            CatUi(id = "1", image = "1", isFavorite = false),
            CatUi(id = "2", image = "2", isFavorite = false)
        )
        val state = MutableStateFlow(RetrofitContracts.State(images = images, isLoading = false))
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { RetrofitScreen(viewModel = viewModel) }
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
            CatUi(id = "1", image = "1", isFavorite = false),
            CatUi(id = "2", image = "2", isFavorite = true)
        )
        val state = MutableStateFlow(RetrofitContracts.State(images = images, isLoading = false))
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { RetrofitScreen(viewModel = viewModel) }
        // THEN
        images.forEach {
            val expectedIconRes = if (it.isFavorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_unfavorite
            }.toString()
            composeTestRule
                .onNodeWithTag("Favorite icon: ${it.id}")
                .assertValueEquals(expectedIconRes)
        }
    }

    @Test
    fun imagesIsEmpty() {
        // GIVEN
        val images = emptyList<CatUi>()
        val state = MutableStateFlow(RetrofitContracts.State(images = images, isLoading = false))
        whenever(viewModel.state).thenReturn(state)
        // WHEN
        composeTestRule.setContent { RetrofitScreen(viewModel = viewModel) }
        // THEN
        composeTestRule
            .onNodeWithTag("List images")
            .onChildren()
            .assertCountEquals(0)
    }
}
