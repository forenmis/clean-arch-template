package com.example.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.screens.homeScreen.HomeScreen
import com.example.presentation.utils.Routes

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) { composable(Routes.HOME) { HomeScreen(viewModel = hiltViewModel()) } }
}
