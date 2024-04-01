package com.example.data.di

import com.example.data.api.CatService
import com.example.data.repository.cat.CatRepositoryImpl
import com.example.data.repository.cat.CatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ApiServiceModule::class])
@InstallIn(SingletonComponent::class)
internal object DataModule {

    @Provides
    fun provideCatRepository(catService: CatService): CatRepository = CatRepositoryImpl(catService)

}