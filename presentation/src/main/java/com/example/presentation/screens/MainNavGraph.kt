package com.example.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.core.Routes
import com.example.presentation.screens.blue.BlueScreen
import com.example.presentation.screens.home.HomeScreen
import com.example.presentation.screens.pink.PinkScreen
import com.example.presentation.screens.vilolet.VioletScreen
import com.example.presentation.screens.yellow.GreenScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = hiltViewModel(),
                onNavigateToGreen = { navController.navigate(Routes.GREEN) },
                onNavigateToPink = { navController.navigate(Routes.PINK) },
                onNavigateToViolet = { navController.navigate(Routes.VIOLET) },
                onNavigateToBlue = { navController.navigate(Routes.BLUE) },
            )
        }
        composable(Routes.BLUE) { BlueScreen(viewModel = hiltViewModel()) }
        composable(Routes.GREEN) { GreenScreen(viewModel = hiltViewModel()) }
        composable(Routes.PINK) { PinkScreen(viewModel = hiltViewModel()) }
        composable(Routes.VIOLET) { VioletScreen(viewModel = hiltViewModel()) }
    }
}