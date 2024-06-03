package com.example.presentation.screens.homescreen

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class HomeContracts {
    data class State(
        val isLoading: Boolean = true,
    ) : BaseState

    sealed interface Event : BaseEvent {
        data object OnNotificationPermissionGranted : Event
    }

    sealed interface Effect : BaseEffect
}
