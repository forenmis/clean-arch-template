package com.example.presentation.screens.retrofitscreen

import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState
import com.example.presentation.screens.retrofitscreen.entity.CatUi

class RetrofitContracts {
    data class State(
        val images: List<CatUi> = emptyList(),
        val isLoading: Boolean = true,
    ) : BaseState

    sealed interface Event : BaseEvent {
        data object OnLoad : Event
        data class OnAddToFavorite(val catUi: CatUi) : Event
        data class OnDeleteFromFavorite(val id: String) : Event
    }

    sealed interface Effect : BaseEffect
}
