package com.example.presentation.screens.playerscreen

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class PlayerContracts {
    data class State(val videoUri: String) : BaseState

    sealed interface Event : BaseEvent

    sealed interface Effect : BaseEffect
}
