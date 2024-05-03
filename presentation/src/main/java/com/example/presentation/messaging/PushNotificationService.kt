package com.example.presentation.messaging

import com.example.common.logger.Logger
import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PushNotificationService : FirebaseMessagingService() {

    @Inject
    lateinit var logger: Logger

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        logger.i("My token is $token")
    }
}
