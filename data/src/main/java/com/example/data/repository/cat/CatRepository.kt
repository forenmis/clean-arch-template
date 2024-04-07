package com.example.data.repository.cat

import com.example.data.entity.CatResponse

interface CatRepository {

    suspend fun getList(): List<CatResponse>
}