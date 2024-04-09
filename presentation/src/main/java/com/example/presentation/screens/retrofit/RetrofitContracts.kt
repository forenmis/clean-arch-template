package com.example.presentation.screens.retrofit

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState
import com.example.presentation.screens.retrofit.entity.CatUi

class RetrofitContracts {
    data class State(
        val images: List<CatUi> = emptyList(),
        val isLoading: Boolean = true
    ) : BaseState

    sealed interface Event : BaseEvent {
        data object OnLoad : Event
    }

    sealed interface Effect : BaseEffect
}
