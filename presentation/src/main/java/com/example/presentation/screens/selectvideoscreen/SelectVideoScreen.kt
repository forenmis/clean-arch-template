package com.example.presentation.screens.selectvideoscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.utils.Palette

@Composable
fun SelectVideoScreen(viewModel: SelectVideoViewModel, onNavigateToPlayer: (String) -> Unit) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SelectVideoContracts.Effect.OpenPlayer -> onNavigateToPlayer(effect.uri)
            }
        }
    }
}

@Composable
private fun ScreenContent(
    state: SelectVideoContracts.State,
    onEvent: (SelectVideoContracts.Event) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.LightPeach)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            state.items.forEach { uri ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    onClick = {
                        onEvent(SelectVideoContracts.Event.OnItemClick(uri))
                    }
                ) {
                    Text(
                        text = uri
                            .removePrefix(
                                prefix = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/"
                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(12.dp)
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
        ScreenContent(state = SelectVideoContracts.State(), onEvent = {})
    }
}
