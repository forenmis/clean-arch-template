package com.example.domain.mapper

import com.example.data.entity.CatResponse
import com.example.domain.entity.CatModel

fun CatResponse.toCatModel(): CatModel = CatModel(image = this.url)
