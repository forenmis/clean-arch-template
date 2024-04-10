package com.example.domain.use_case

import com.example.data.repository.cat.CatRepository
import javax.inject.Inject

class DeleteFromFavoriteUseCase @Inject constructor(private val catRepository: CatRepository) {
    suspend fun execute(id: String) {
        catRepository.deleteFromFavorites(id)
    }
}