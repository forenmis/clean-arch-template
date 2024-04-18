package com.example.presentation.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<
    State : BaseState,
    Event : BaseEvent,
    Effect : BaseEffect,
    >(initialState: State) : ViewModel() {

    private val _state = MutableStateFlow<State>(initialState)
    internal val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<Effect>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    internal val effect = _effect.asSharedFlow()

    internal open fun onEvent(event: Event) {}

    protected fun setState(function: (State) -> State) {
        _state.update(function)
    }

    protected fun setEffect(effect: Effect) {
        _effect.tryEmit(effect)
    }
}
