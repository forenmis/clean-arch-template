package com.example.presentation.ads

import android.app.Activity
import android.content.Context
import com.example.common.logger.Logger
import com.example.presentation.BuildConfig
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
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

    fun interstitialAd(activity: Activity) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            activity,
            BuildConfig.INTERSTITIAL_AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    logger.d(adError.message)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    logger.d("Ad was loaded.")
                    interstitialAd.show(activity)
                }
            }
        )
    }
}
