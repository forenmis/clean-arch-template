package com.example.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.GetCatListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCatListUseCase: GetCatListUseCase) :
    ViewModel() {

    fun load() = viewModelScope.launch {
        val list = getCatListUseCase.execute()
        Log.e("LIST", list.joinToString())
    }


}