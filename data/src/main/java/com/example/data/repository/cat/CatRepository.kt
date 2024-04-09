package com.example.data.repository.cat

import com.example.data.database.entity.CatData
import com.example.data.network.entity.CatResponse

interface CatRepository {

    suspend fun getList(): List<CatResponse>

    suspend fun getFavorites(): List<CatData>

    suspend fun deleteFromFavorites(id: String)

    suspend fun addToFavorites(cat: CatData)
}