package com.example.presentation.di

import android.app.Application
import com.example.common.logger.Logger
import com.example.presentation.ads.AdsProvider
import com.example.presentation.ads.AdsProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object PresentationModule {
    @Provides
    fun provideAdsProvider(context: Application, logger: Logger): AdsProvider {
        return AdsProviderImpl(context, logger)
    }
}
