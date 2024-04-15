package com.example.data.di

import com.example.data.database.dao.CatDao
import com.example.data.database.di.DatabaseModule
import com.example.data.network.api.CatService
import com.example.data.network.di.ApiServiceModule
import com.example.data.repository.cat.CatRepository
import com.example.data.repository.cat.CatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [ApiServiceModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    fun provideCatRepository(
        catService: CatService,
        catDao: CatDao,
    ): CatRepository = CatRepositoryImpl(catService, catDao)
}
