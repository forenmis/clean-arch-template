package com.example.presentation.screens.playerscreen

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.presentation.BuildConfig
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() :
    BaseViewModel<PlayerContracts.State, PlayerContracts.Event, PlayerContracts.Effect>(
        initialState = PlayerContracts.State(BuildConfig.EXAMPLE_VIDEO_URI)
    ) {
    fun createPlayer(context: Context): ExoPlayer = ExoPlayer.Builder(context).build()
}
