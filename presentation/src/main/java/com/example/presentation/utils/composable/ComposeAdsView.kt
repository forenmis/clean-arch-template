package com.example.presentation.utils.composable

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.presentation.BuildConfig
import com.example.presentation.databinding.SmallTemplateViewBinding
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.nativead.NativeAdOptions

@Composable
fun ComposeBannerAdsView(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = BuildConfig.BANNER_AD_UNIT_ID
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

@Composable
fun ComposeNativeAdsView(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    AndroidViewBinding(
        modifier = modifier,
        factory = { inflater: LayoutInflater, parent: ViewGroup, _ ->
            val binding = SmallTemplateViewBinding.inflate(inflater, parent)
            val adLoader = AdLoader.Builder(context, BuildConfig.NATIVE_AD_UNIT_ID)
                .forNativeAd { nativeAd ->
                    with(binding) {
                        nativeAdView.setNativeAd(nativeAd)
                        nativeAdView.callToActionView = cta
                        primary.text = nativeAd.headline
                        ratingBar.rating = nativeAd.starRating?.toFloat() ?: 0f
                        cta.text = nativeAd.callToAction
                        icon.setImageDrawable(nativeAd.images.firstOrNull()?.drawable)
                    }
                }.withNativeAdOptions(NativeAdOptions.Builder().build())
                .build()
            adLoader.loadAd(AdRequest.Builder().build())
            binding
        }
    )
}
