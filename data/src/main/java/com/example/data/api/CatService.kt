package com.example.data.api

import com.example.data.entity.CatResponse
import retrofit2.http.GET

interface CatService {

    @GET("v1/images/search?limit=10")
    suspend fun getCats(): List<CatResponse>
}
