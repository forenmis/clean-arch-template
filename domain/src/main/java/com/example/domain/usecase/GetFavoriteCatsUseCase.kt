package com.example.domain.usecase

import com.example.data.repository.cat.CatRepository
import com.example.domain.entity.CatModel
import com.example.domain.mapper.toCatModel
import javax.inject.Inject

class GetFavoriteCatsUseCase @Inject constructor(private val catRepository: CatRepository) {
    suspend fun execute(): List<CatModel> {
        return catRepository.getFavorites().map { it.toCatModel() }
    }
}
