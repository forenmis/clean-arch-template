package com.example.presentation.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.os.BundleCompat
import com.example.presentation.utils.SmsHelper
import com.google.android.gms.common.api.Status
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes

class SMSBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("smsRetriever", "onReceive")
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action && intent.extras != null) {
            val extras = requireNotNull(intent.extras)
            val status = BundleCompat.getParcelable(
                extras,
                SmsRetriever.EXTRA_STATUS,
                Status::class.java
            )
            when (status?.statusCode) {
                CommonStatusCodes.SUCCESS -> {
                    val message = extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE)
                    SmsHelper.updateSmsMessage(message)
                    Log.d("smsRetriever", "success, $message")
                }

                CommonStatusCodes.TIMEOUT -> {
                    // Waiting for SMS timed out (5 minutes)
                    // Handle the error ...
                    Log.d("smsRetriever", "timeout")
                }
            }
        }
    }
}
