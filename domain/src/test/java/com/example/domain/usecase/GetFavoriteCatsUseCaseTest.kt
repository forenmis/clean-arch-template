package com.example.domain.usecase

import com.example.data.database.entity.CatData
import com.example.data.repository.cat.CatRepository
import com.example.domain.entity.CatModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetFavoriteCatsUseCaseTest {
    private val catRepository = mock<CatRepository>()
    private val getFavoriteCatsUseCase = GetFavoriteCatsUseCase(catRepository)

    @Test
    fun verifyExecution() = runTest {
        //GIVEN
        whenever(catRepository.getFavorites()).thenReturn(createList())
        val expected = listOf(CatModel("1", "1"))
        //WHEN
        val actual = getFavoriteCatsUseCase.execute()
        //THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyEmptyList() = runTest {
        //GIVEN
        whenever(catRepository.getFavorites()).thenReturn(emptyList())
        val expected = emptyList<CatData>()
        //WHEN
        val actual = getFavoriteCatsUseCase.execute()
        //THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyException() = runTest {
        //GIVEN
        val expected = Throwable()
        whenever(catRepository.getFavorites()).doAnswer { throw expected }
        //WHEN
        val actual = runCatching { getFavoriteCatsUseCase.execute() }.exceptionOrNull()
        //THEN
        assertEquals(expected, actual)
    }

    private fun createList(): List<CatData> {
        return listOf(CatData("1", "1"))
    }
}
