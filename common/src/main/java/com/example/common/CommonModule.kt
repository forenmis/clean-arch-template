package com.example.common

import com.example.common.logger.Logger
import com.example.common.logger.LoggerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object CommonModule {
    @Provides
    fun provideLogger(): Logger = LoggerImpl()
}
