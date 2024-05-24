package com.example.domain.usecase

import com.example.data.network.entity.CatResponse
import com.example.data.repository.cat.CatRepository
import com.example.domain.entity.CatModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCatListUseCaseTest {
    private val catRepository = mock<CatRepository>()
    private val getCatListUseCase = GetCatListUseCase(catRepository)

    @Test
    fun verifyExecution() = runTest {
        //GIVEN
        whenever(catRepository.getList()).thenReturn(createList())
        val expected = listOf(CatModel("1", "1"))
        //WHEN
        val actual = getCatListUseCase.execute()
        //THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyEmptyList() = runTest {
        //GIVEN
        whenever(catRepository.getList()).thenReturn(emptyList())
        val expected = emptyList<CatResponse>()
        //WHEN
        val actual = getCatListUseCase.execute()
        //THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyException() = runTest {
        //GIVEN
        val expected = Throwable()
        whenever(catRepository.getList()).doAnswer { throw expected }
        //WHEN
        val actual = runCatching { getCatListUseCase.execute() }.exceptionOrNull()
        //THEN
        assertEquals(expected, actual)
    }

    private fun createList(): List<CatResponse> {
        return listOf(CatResponse("1", "1"))
    }
}
