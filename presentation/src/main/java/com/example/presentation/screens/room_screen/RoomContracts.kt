package com.example.presentation.screens.room_screen

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState
import com.example.presentation.screens.retrofit_screen.entity.CatUi

class RoomContracts {
    data class State(
        val images: List<CatUi> = emptyList(),
        val isLoading: Boolean = false,
    ) : BaseState

    sealed interface Event : BaseEvent {
        data object OnLoad : Event
        data class OnDelete(val id: String) : Event

    }

    sealed interface Effect : BaseEffect
}
