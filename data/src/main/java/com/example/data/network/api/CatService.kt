package com.example.data.network.api

import com.example.data.network.entity.CatResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("v1/images/search")
    suspend fun getCats(@Query("limit") limit: Int = 10): List<CatResponse>
}
