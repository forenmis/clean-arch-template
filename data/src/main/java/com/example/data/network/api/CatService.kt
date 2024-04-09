package com.example.data.network.api

import com.example.data.network.entity.CatResponse
import retrofit2.http.GET

interface CatService {
    @GET("v1/images/search?limit=10")
    suspend fun getCats(): List<CatResponse>






}




