package com.example.presentation.screens.pink

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.GetCatListUseCase
import com.example.presentation.core.BaseViewModel
import com.example.presentation.screens.pink.mapper.toCatUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PinkViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase
) : BaseViewModel<PinkContracts.State, PinkContracts.Event, PinkContracts.Effect>(PinkContracts.State()) {

    override fun onEvent(event: PinkContracts.Event) {
        super.onEvent(event)
        when (event) {
            PinkContracts.Event.OnLoad -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        setState { it.copy(isLoading = true) }
        val list = getCatListUseCase.execute().map { it.toCatUi() }
        setState { it.copy(images = list, isLoading = false) }
    }
}