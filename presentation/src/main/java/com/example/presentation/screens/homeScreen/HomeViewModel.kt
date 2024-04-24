package com.example.presentation.screens.homeScreen

import com.example.common.logger.Logger
import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(logger: Logger) : BaseViewModel<
    HomeContracts.State,
    HomeContracts.Event,
    HomeContracts.Effect
    >(HomeContracts.State()) {
    init {
        logger.i("HomeViewModel created")
    }
}
