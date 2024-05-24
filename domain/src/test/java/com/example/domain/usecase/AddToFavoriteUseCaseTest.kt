package com.example.domain.usecase

import com.example.data.repository.cat.CatRepository
import com.example.domain.entity.CatModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class AddToFavoriteUseCaseTest {
    private val catRepository = mock<CatRepository>()
    private val addToFavoriteUseCase = AddToFavoriteUseCase(catRepository)

    @Test
    fun verifyExecution() = runTest {
        //GIVEN
        whenever(catRepository.addToFavorites(any())).thenReturn(Unit)
        val catModel = CatModel(id = "1", image = "1")
        val expected = Unit
        //WHEN
        val actual = addToFavoriteUseCase.execute(catModel)
        //THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyExecutionException() = runTest {
        //GIVEN
        val expected = Throwable()
        whenever(catRepository.addToFavorites(any())).doAnswer { throw expected }
        val catModel = CatModel(id = "1", image = "1")
        //WHEN
        val actual = runCatching { addToFavoriteUseCase.execute(catModel) }.exceptionOrNull()
        //THEN
        assertEquals(expected, actual)
    }
}