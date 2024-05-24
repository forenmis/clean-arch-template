package com.example.data.repository.cat

import com.example.data.database.dao.CatDao
import com.example.data.database.entity.CatData
import com.example.data.network.api.CatService
import com.example.data.network.entity.CatResponse
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CatRepositoryImplTest {
    private val catService = mock<CatService>()
    private val catDao = mock<CatDao>()
    private val catRepository = CatRepositoryImpl(catService, catDao)

    @Test
    fun verifyGetList() = runTest {
        // GIVEN
        whenever(catService.getCats()).thenReturn(createListResp())
        val expected = createListResp()
        // WHEN
        val actual = catRepository.getList()
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyGetFavorites() = runTest {
        // GIVEN
        whenever(catDao.getFavorites()).thenReturn(createListData())
        val expected = createListData()
        // WHEN
        val actual = catRepository.getFavorites()
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyDeleteFromFavorites() = runTest {
        // GIVEN
        val id = "1"
        whenever(catDao.deleteFromFavorites(id)).thenReturn(Unit)
        val expected = Unit
        // WHEN
        val actual = catRepository.deleteFromFavorites(id)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun verifyAddToFavorites() = runTest {
        // GIVEN
        whenever(catDao.addToFavorites(any())).thenReturn(Unit)
        val expected = Unit
        // WHEN
        val actual = catRepository.addToFavorites(CatData("1", "1"))
        // THEN
        assertEquals(expected, actual)
    }

    private fun createListResp(): List<CatResponse> {
        return listOf(CatResponse("1", "1"))
    }

    private fun createListData(): List<CatData> {
        return listOf(CatData("1", "1"))
    }
}
