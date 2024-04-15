package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.database.entity.CatData

@Dao
interface CatDao {
    @Insert
    suspend fun addToFavorites(cat: CatData)

    @Query("SELECT * FROM cat_images")
    suspend fun getFavorites(): List<CatData>

    @Query("DELETE FROM cat_images WHERE id = :id")
    suspend fun deleteFromFavorites(id: String)

}