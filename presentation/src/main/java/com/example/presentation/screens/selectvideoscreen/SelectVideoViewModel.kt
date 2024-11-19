package com.example.presentation.screens.selectvideoscreen

import com.example.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectVideoViewModel @Inject constructor() : BaseViewModel<
    SelectVideoContracts.State,
    SelectVideoContracts.Event,
    SelectVideoContracts.Effect
    >(
    SelectVideoContracts.State()
) {
    override fun onEvent(event: SelectVideoContracts.Event) {
        super.onEvent(event)
        when (event) {
            is SelectVideoContracts.Event.OnItemClick -> openVideo(event.uri)
        }
    }

    private fun openVideo(uri: String) {
        setEffect(SelectVideoContracts.Effect.OpenPlayer(uri))
    }
}
