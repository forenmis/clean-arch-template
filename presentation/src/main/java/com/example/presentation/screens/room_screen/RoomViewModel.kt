package com.example.presentation.screens.room_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.DeleteFromFavoriteUseCase
import com.example.domain.use_case.GetFavoriteCatsUseCase
import com.example.presentation.core.BaseViewModel
import com.example.presentation.mapper.toCatUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
) : BaseViewModel<
        RoomContracts.State,
        RoomContracts.Event,
        RoomContracts.Effect>(RoomContracts.State()) {

    override fun onEvent(event: RoomContracts.Event) {
        super.onEvent(event)
        when (event) {
            is RoomContracts.Event.OnLoad -> load()
            is RoomContracts.Event.OnDelete -> {
                deleteFromFavorite(event.id)
                load()
            }
        }
    }

    private fun load() = viewModelScope.launch {
        setState { it.copy(isLoading = true) }
        val list = getFavoriteCatsUseCase.execute().map { it.toCatUi(isFavorite = true) }
        setState { it.copy(images = list, isLoading = false) }
    }

    private fun deleteFromFavorite(id: String) = viewModelScope.launch {
        deleteFromFavoriteUseCase.execute(id)
    }

}
