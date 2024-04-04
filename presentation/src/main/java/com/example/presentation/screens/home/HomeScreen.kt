package com.example.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.core.BottomRoute
import com.example.presentation.core.Palette
import com.example.presentation.screens.blue.BlueScreen
import com.example.presentation.screens.pink.PinkScreen
import com.example.presentation.screens.vilolet.VioletScreen
import com.example.presentation.screens.yellow.GreenScreen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.load()
    }
    MaterialTheme {
        ScreenContent()
    }
}

@Composable
private fun ScreenContent() {
    val bottomBarNavController = rememberNavController()
    val screenRoutes = BottomRoute.all()
    Scaffold(bottomBar = {
        NavigationBar(containerColor = Palette.Yellow) {
            val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            screenRoutes.forEach { screen ->
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null, tint = Palette.Peach) },
                    label = { Text(text = screen.route, color = Palette.Peach) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        bottomBarNavController.navigate(screen.route) {
                            popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }) { innerPadding ->
        NavHost(
            bottomBarNavController,
            startDestination = screenRoutes.first().route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomRoute.Blue.route) { BlueScreen(viewModel = hiltViewModel()) }
            composable(BottomRoute.Green.route) { GreenScreen(viewModel = hiltViewModel()) }
            composable(BottomRoute.Pink.route) { PinkScreen(viewModel = hiltViewModel()) }
            composable(BottomRoute.Violet.route) { VioletScreen(viewModel = hiltViewModel()) }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    ScreenContent(

    )
}
