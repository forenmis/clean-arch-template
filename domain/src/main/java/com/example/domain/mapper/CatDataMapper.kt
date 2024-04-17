package com.example.domain.mapper

import com.example.data.database.entity.CatData
import com.example.domain.entity.CatModel

fun CatData.toCatModel(): CatModel = CatModel(id = id, image = this.image)

fun CatModel.toCatData(): CatData = CatData(id = id, image = this.image)
