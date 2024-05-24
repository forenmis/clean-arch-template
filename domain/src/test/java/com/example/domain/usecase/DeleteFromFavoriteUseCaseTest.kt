package com.example.domain.usecase

import com.example.data.repository.cat.CatRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class DeleteFromFavoriteUseCaseTest {
    private val catRepository = mock<CatRepository>()
    private val deleteFromFavoriteUseCase = DeleteFromFavoriteUseCase(catRepository)

    @Test
    fun verifyExecution() = runTest {
        // GIVEN
        whenever(catRepository.deleteFromFavorites(any())).thenReturn(Unit)
        val expected = Unit
        // WHEN
        val actual = deleteFromFavoriteUseCase.execute("1")
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyExecutionException() = runTest {
        // GIVEN
        val expected = Throwable()
        whenever(catRepository.deleteFromFavorites(any())).doAnswer { throw expected }
        // WHEN
        val actual = runCatching { deleteFromFavoriteUseCase.execute("1") }.exceptionOrNull()
        // THEN
        assertEquals(expected, actual)
    }
}
