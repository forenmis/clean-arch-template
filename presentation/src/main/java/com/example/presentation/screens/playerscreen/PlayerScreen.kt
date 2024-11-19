package com.example.presentation.screens.playerscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.screens.sharedcomponents.ExoPlayerView
import com.example.presentation.utils.Palette

@Composable
fun PlayerScreen(viewModel: PlayerViewModel, uri: String) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) { viewModel.createPlayer(context, uri) }

    ScreenContent(state = state)
}

@Composable
private fun ScreenContent(state: PlayerContracts.State) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Palette.Blue),
        contentAlignment = Alignment.Center
    ) {
        if (state is PlayerContracts.State.Ready) {
            ExoPlayerView(
                exoPlayer = state.exoPlayer,
                videoUri = state.videoUri,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = PlayerContracts.State.Loading
        )
    }
}
