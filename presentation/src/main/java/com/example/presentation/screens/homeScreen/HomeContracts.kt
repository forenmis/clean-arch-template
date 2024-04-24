package com.example.presentation.screens.homeScreen

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class HomeContracts {
    data class State(
        val isLoading: Boolean = true,
    ) : BaseState

    sealed interface Event : BaseEvent

    sealed interface Effect : BaseEffect
}
