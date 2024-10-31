package com.example.presentation.screens.playerscreen

import androidx.media3.exoplayer.ExoPlayer
import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class PlayerContracts {
    sealed interface State : BaseState {
        data object Loading : State
        data class Ready(
            val videoUri: String,
            val exoPlayer: ExoPlayer,
        ) : State
    }

    sealed interface Event : BaseEvent

    sealed interface Effect : BaseEffect
}
