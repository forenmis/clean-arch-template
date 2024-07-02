package com.example.presentation.main

import androidx.lifecycle.viewModelScope
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    BaseViewModel<
        MainContracts.State,
        MainContracts.Event,
        MainContracts.Effect
        >(initialState = MainContracts.State()) {

    init {
        viewModelScope.launch {
            delay(DELAY_IN_MS)
            setState {
                it.copy(isLoading = false)
            }
        }
    }

    companion object {
        private const val DELAY_IN_MS: Long = 3_000
    }
}
