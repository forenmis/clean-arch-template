package com.example.presentation.screens.pink.mapper

import com.example.domain.entity.CatModel
import com.example.presentation.screens.pink.entity.CatUi

fun CatModel.toCatUi(): CatUi = CatUi(image = this.image)
