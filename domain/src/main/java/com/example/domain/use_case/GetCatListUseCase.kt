package com.example.domain.use_case

import com.example.data.repository.cat.CatRepository
import com.example.domain.entity.CatModel
import javax.inject.Inject

class GetCatListUseCase @Inject constructor(private val catRepository: CatRepository) {
    suspend fun execute(): List<CatModel> {
        return catRepository.getList().map { response -> CatModel(name = response.name) }
    }
}