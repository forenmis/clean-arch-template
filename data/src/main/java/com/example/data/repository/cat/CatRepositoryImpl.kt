package com.example.data.repository.cat

import com.example.data.api.CatService
import com.example.data.entity.CatResponse

internal class CatRepositoryImpl(private val catService: CatService) : CatRepository {
    override suspend fun getList(): List<CatResponse> {
       return listOf(
           CatResponse("A"),
           CatResponse("B"),
           CatResponse("C"),
           CatResponse("D"),
       )
    }

    override suspend fun getRandomItem(): CatResponse {
        TODO("Not yet implemented")
    }
}