package com.example.presentation.screens.retrofit_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.AddToFavoriteUseCase
import com.example.domain.use_case.DeleteFromFavoriteUseCase
import com.example.domain.use_case.GetCatListUseCase
import com.example.domain.use_case.GetFavoriteCatsUseCase
import com.example.presentation.core.BaseViewModel
import com.example.presentation.mapper.toCatModel
import com.example.presentation.mapper.toCatUi
import com.example.presentation.screens.retrofit_screen.entity.CatUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase,
    private val addToFavoriteUseCase: AddToFavoriteUseCase,
    private val deleteFromFavoriteUseCase: DeleteFromFavoriteUseCase,
    private val getFavoriteCatsUseCase: GetFavoriteCatsUseCase,
) : BaseViewModel<RetrofitContracts.State, RetrofitContracts.Event, RetrofitContracts.Effect>(
    RetrofitContracts.State()
) {
    private val favoritesIds = mutableListOf<String>()

    init {
        viewModelScope.launch {
            favoritesIds += loadFavorites()
        }
    }

    override fun onEvent(event: RetrofitContracts.Event) {
        super.onEvent(event)
        when (event) {
            is RetrofitContracts.Event.OnLoad -> load()
            is RetrofitContracts.Event.OnAddToFavorite -> saveToFavorite(event.catUi)
            is RetrofitContracts.Event.OnDeleteFromFavorite -> deleteFromFavorite(event.id)
        }
    }

    private fun load() = viewModelScope.launch {
        setState { it.copy(isLoading = true) }
        val list = getCatListUseCase.execute()
            .map { it.toCatUi(isFavorite = favoritesIds.contains(it.id)) }
        setState { it.copy(images = list, isLoading = false) }
    }

    private fun saveToFavorite(catUi: CatUi) = viewModelScope.launch {
        addToFavoriteUseCase.execute(catUi.toCatModel())
        setState { state ->
            val newList = state.images.map { if (it == catUi) it.copy(isFavorite = true) else it }
            state.copy(images = newList)
        }
    }

    private fun deleteFromFavorite(id: String) = viewModelScope.launch {
        deleteFromFavoriteUseCase.execute(id)
        setState { state ->
            val newList = state.images.map { if (it.id == id) it.copy(isFavorite = false) else it }
            state.copy(images = newList)
        }
    }

    private suspend fun loadFavorites(): List<String> = getFavoriteCatsUseCase.execute()
        .map { it.id }
}
