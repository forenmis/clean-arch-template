package com.example.presentation.screens.welcomescreen

import android.app.Activity
import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class WelcomeContracts {
    data class State(
        val isEnabledVerify: Boolean = false,
        val phoneNumber: String = "",
        val isPhoneNumberValid: Boolean = true,
        val verificationCode: String = "",
        val verificationCodeSent: Boolean = false,
    ) : BaseState

    sealed interface Event : BaseEvent {
        data class OnSendPhone(val activity: Activity) : Event
        data class OnPhoneNumberChanged(val number: String) : Event
        data object OnSendVerifyCode : Event
        data class OnVerifyCodeChanged(val code: String) : Event
    }

    sealed interface Effect : BaseEffect {
        data object OnVerificationCompleted : Effect
        data object OnVerificationFailed : Effect
    }
}
