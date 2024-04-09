package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.BuildConfig
import com.example.data.database.dao.CatDao
import com.example.data.database.entity.CatData

@Database(
    entities = [CatData::class],
    version = BuildConfig.DATABASE_VERSION
)
internal abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}
