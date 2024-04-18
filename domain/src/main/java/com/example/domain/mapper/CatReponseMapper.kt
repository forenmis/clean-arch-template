package com.example.domain.mapper

import com.example.data.network.entity.CatResponse
import com.example.domain.entity.CatModel

fun CatResponse.toCatModel(): CatModel = CatModel(id = id, image = this.url)
