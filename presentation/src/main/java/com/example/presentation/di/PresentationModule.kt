package com.example.presentation.di

import android.app.Application
import com.example.common.logger.Logger
import com.example.presentation.ads.AdsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object PresentationModule {
    @Provides
    fun provideAdsHelper(context: Application, logger: Logger): AdsHelper {
        return AdsHelper(context, logger)
    }
}
