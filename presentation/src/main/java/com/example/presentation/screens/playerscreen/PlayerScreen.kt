package com.example.presentation.screens.playerscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.exoplayer.ExoPlayer
import com.example.presentation.screens.sharedcomponents.ExoPlayerView
import com.example.presentation.utils.Palette

@SuppressWarnings("UnusedParameter")
@Composable
fun PlayerScreen(viewModel: PlayerViewModel) {
    val context = LocalContext.current
    val exoPlayer = viewModel.createPlayer(context)
    val state by viewModel.state.collectAsStateWithLifecycle()

    ScreenContent(exoPlayer = exoPlayer, state = state)
}

@Composable
private fun ScreenContent(exoPlayer: ExoPlayer, state: PlayerContracts.State) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Palette.Blue),
        contentAlignment = Alignment.Center
    ) {
        ExoPlayerView(
            exoPlayer = exoPlayer,
            videoUri = state.videoUri,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent(
            exoPlayer = ExoPlayer.Builder(LocalContext.current).build(),
            state = PlayerContracts.State("q")
        )
    }
}
