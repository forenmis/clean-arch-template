package com.example.presentation.screens.retrofit.mapper

import com.example.domain.entity.CatModel
import com.example.presentation.screens.retrofit.entity.CatUi

fun CatModel.toCatUi(): CatUi = CatUi(
    id = id,
    image = this.image
)
