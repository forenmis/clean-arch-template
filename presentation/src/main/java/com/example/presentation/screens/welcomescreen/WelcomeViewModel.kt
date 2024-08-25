@file:Suppress("SwallowedException", "TooGenericExceptionCaught")

package com.example.presentation.screens.welcomescreen

import android.app.Activity
import androidx.lifecycle.viewModelScope
import com.example.presentation.core.BaseViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : BaseViewModel<
    WelcomeContracts.State,
    WelcomeContracts.Event,
    WelcomeContracts.Effect
    >(WelcomeContracts.State()) {
    private var verificationId: String? = null

    override fun onEvent(event: WelcomeContracts.Event) {
        super.onEvent(event)
        when (event) {
            is WelcomeContracts.Event.OnPhoneNumberChanged -> changePhoneNumber(event.number)
            is WelcomeContracts.Event.OnSendPhone -> onVerifyClick(event.activity)
            is WelcomeContracts.Event.OnVerifyCodeChanged -> changeVerifyCode(event.code)
            is WelcomeContracts.Event.OnSendVerifyCode -> verifyCode()
        }
    }

    private fun changePhoneNumber(newNumber: String) {
        setState {
            it.copy(
                phoneNumber = newNumber,
                isEnabledVerify = newNumber.firstOrNull() == '+' && newNumber.length == 13
            )
        }
    }

    private fun changeVerifyCode(newCode: String) {
        setState {
            it.copy(
                verificationCode = newCode
            )
        }
    }

    private fun onVerifyClick(activity: Activity) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                signIn(p0)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                setEffect(WelcomeContracts.Effect.OnVerificationFailed)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                super.onCodeSent(verificationId, token)
                this@WelcomeViewModel.verificationId = verificationId
            }
        }
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
            .setPhoneNumber(state.value.phoneNumber) // Phone number to verify
            .setTimeout(TIMEOUT, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        setState { it.copy(verificationCodeSent = true) }
    }

    private fun verifyCode() {
        verificationId?.let {
            val credential = PhoneAuthProvider.getCredential(it, state.value.verificationCode)
            signIn(credential)
        }
    }

    private fun signIn(credential: PhoneAuthCredential) = viewModelScope.launch {
        try {
            Firebase.auth.signInWithCredential(credential)
                .await()
        } catch (exception: Throwable) {
            setEffect(WelcomeContracts.Effect.OnVerificationFailed)
        }

        setEffect(WelcomeContracts.Effect.OnVerificationCompleted)
    }

    companion object {
        private const val TIMEOUT = 30L
    }
}
