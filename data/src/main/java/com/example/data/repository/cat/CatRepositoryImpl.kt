package com.example.data.repository.cat

import com.example.data.database.entity.CatData
import com.example.data.network.api.CatService
import com.example.data.network.entity.CatResponse

internal class CatRepositoryImpl(private val catService: CatService) : CatRepository {
    override suspend fun getList(): List<CatResponse> {
        catService.getCats().runCatching {
            return this
        }
        return emptyList()
    }

    override suspend fun getFavorites(): List<CatData> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFromFavorites(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorites(cat: CatData) {
        TODO("Not yet implemented")
    }
}