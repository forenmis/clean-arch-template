package com.example.presentation.screens.homescreen

import androidx.lifecycle.viewModelScope
import com.example.common.logger.Logger
import com.example.presentation.core.BaseViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val logger: Logger) : BaseViewModel<
    HomeContracts.State,
    HomeContracts.Event,
    HomeContracts.Effect
    >(HomeContracts.State()) {
    init {
        logger.i("HomeViewModel created")
    }

    override fun onEvent(event: HomeContracts.Event) {
        super.onEvent(event)
        when (event) {
            HomeContracts.Event.OnNotificationPermissionGranted -> onNotificationPermissionGranted()
        }
    }

    private fun onNotificationPermissionGranted() = viewModelScope.launch {
        val token = Firebase.messaging.token.await()
        logger.i("Token : $token")
    }
}
