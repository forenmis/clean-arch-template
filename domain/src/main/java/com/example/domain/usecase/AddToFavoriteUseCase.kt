package com.example.domain.usecase

import com.example.data.repository.cat.CatRepository
import com.example.domain.entity.CatModel
import com.example.domain.mapper.toCatData
import javax.inject.Inject

class AddToFavoriteUseCase @Inject constructor(private val catRepository: CatRepository) {
    suspend fun execute(catModel: CatModel) {
        catRepository.addToFavorites(catModel.toCatData())
    }
}
