package com.example.presentation.mapper

import com.example.domain.entity.CatModel
import com.example.presentation.screens.retrofit_screen.entity.CatUi

fun CatModel.toCatUi(isFavorite: Boolean = false): CatUi = CatUi(
    id = id,
    image = this.image,
    isFavorite = isFavorite
)

fun CatUi.toCatModel(): CatModel = CatModel(
    id = id,
    image = this.image
)
