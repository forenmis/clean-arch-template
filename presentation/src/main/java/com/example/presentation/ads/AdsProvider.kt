package com.example.presentation.ads

import android.app.Activity

interface AdsProvider {

    fun initialize()

    fun interstitialAd(activity: Activity)
}
