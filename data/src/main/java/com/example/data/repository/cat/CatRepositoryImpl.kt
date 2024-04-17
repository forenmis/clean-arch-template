package com.example.data.repository.cat

import com.example.data.database.dao.CatDao
import com.example.data.database.entity.CatData
import com.example.data.network.api.CatService
import com.example.data.network.entity.CatResponse

internal class CatRepositoryImpl(
    private val catService: CatService,
    private val catDao: CatDao,
) : CatRepository {
    override suspend fun getList(): List<CatResponse> {
        catService.getCats().runCatching {
            return this
        }
        return emptyList()
    }

    override suspend fun getFavorites(): List<CatData> {
        return catDao.getFavorites()
    }

    override suspend fun deleteFromFavorites(id: String) {
        catDao.deleteFromFavorites(id)
    }

    override suspend fun addToFavorites(cat: CatData) {
        catDao.addToFavorites(cat)
    }
}
