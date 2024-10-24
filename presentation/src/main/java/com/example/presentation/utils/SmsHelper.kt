package com.example.presentation.utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object SmsHelper {
    private const val CODE_COUNT: Int = 6

    private val _smsMessage = MutableSharedFlow<String>()
    val smsMessage = _smsMessage.asSharedFlow()

    @OptIn(DelicateCoroutinesApi::class)
    fun updateSmsMessage(message: String?) = GlobalScope.launch {
        message?.let { _smsMessage.emit(message.take(CODE_COUNT)) }
    }
}
