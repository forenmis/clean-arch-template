package com.example.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.presentation.screens.homescreen.HomeScreen
import com.example.presentation.screens.playerscreen.PlayerScreen
import com.example.presentation.utils.Home
import com.example.presentation.utils.Player

@Composable
fun MainNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        modifier = modifier.semantics { this.testTag = "nav graph" },
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel = hiltViewModel(),
                onNavigateToPlayer = { uri ->
                    navController.navigate(Player(uri))
                }
            )
        }
        composable<Player> { backStackEntry ->
            val player: Player = backStackEntry.toRoute()
            PlayerScreen(
                viewModel = hiltViewModel(),
                uri = player.uri
            )
        }
    }
}
