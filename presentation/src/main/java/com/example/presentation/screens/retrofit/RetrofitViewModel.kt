package com.example.presentation.screens.retrofit

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.GetCatListUseCase
import com.example.presentation.core.BaseViewModel
import com.example.presentation.screens.retrofit.mapper.toCatUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RetrofitViewModel @Inject constructor(
    private val getCatListUseCase: GetCatListUseCase
) : BaseViewModel<RetrofitContracts.State, RetrofitContracts.Event, RetrofitContracts.Effect>(RetrofitContracts.State()) {

    override fun onEvent(event: RetrofitContracts.Event) {
        super.onEvent(event)
        when (event) {
            RetrofitContracts.Event.OnLoad -> load()
        }
    }

    private fun load() = viewModelScope.launch {
        setState { it.copy(isLoading = true) }
        val list = getCatListUseCase.execute().map { it.toCatUi() }
        setState { it.copy(images = list, isLoading = false) }
    }
}
