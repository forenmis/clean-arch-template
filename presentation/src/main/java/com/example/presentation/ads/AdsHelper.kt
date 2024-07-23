package com.example.presentation.ads

import android.content.Context
import com.example.common.logger.Logger
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AdsHelper(private val context: Context, private val logger: Logger) {
    private val backgroundScope = CoroutineScope(Dispatchers.IO)

    fun initialize() {
        backgroundScope.launch {
            MobileAds.initialize(context) { initializationStatus ->
                initializationStatus.adapterStatusMap
                    .entries
                    .forEach { entry ->
                        logger.w(
                            "MobileAds.initialize:\n" +
                                "state=${entry.key}, " +
                                "state=${entry.value.initializationState}, " +
                                "desc=${entry.value.description}"
                        )
                    }
            }
        }
    }
}
