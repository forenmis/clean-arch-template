package com.example.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    LaunchedEffect(Unit) {
        viewModel.load()
    }
     Column {
         Text(text = "Text")
     }

}

