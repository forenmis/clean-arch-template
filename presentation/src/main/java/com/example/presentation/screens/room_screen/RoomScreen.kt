package com.example.presentation.screens.room_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.screens.shared_components.Animation
import com.example.presentation.screens.shared_components.ListItem
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette

@Composable
fun RoomScreen(viewModel: RoomViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.onEvent(RoomContracts.Event.OnLoad) }

    ScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun ScreenContent(
    state: RoomContracts.State,
    onEvent: (RoomContracts.Event) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.LightPeach)
    ) {
        if (state.images.isEmpty()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Animation(resId = R.raw.empty_favorite)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(Dimens.BasePadding)
            ) {
                items(state.images) { catUi ->
                    ListItem(
                        catUi = catUi,
                        onDeleteFromFavorite = { onEvent(RoomContracts.Event.OnDelete(it.id)) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent(state = RoomContracts.State(), onEvent = {})
    }
}
