package com.example.presentation.main

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class MainContracts {
    data class State(
        val isLoading: Boolean = true,
    ) : BaseState

    sealed interface Event : BaseEvent

    sealed interface Effect : BaseEffect
}
