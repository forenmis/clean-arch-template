package com.example.data.database.di

import android.app.Application
import androidx.room.Room
import com.example.data.BuildConfig
import com.example.data.database.CatDatabase
import com.example.data.database.dao.CatDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    fun provideCatDatabase(context: Application): CatDatabase {
        return Room.databaseBuilder(
            context,
            CatDatabase::class.java,
            BuildConfig.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideCatDao(catDatabase: CatDatabase): CatDao = catDatabase.catDao()
}
