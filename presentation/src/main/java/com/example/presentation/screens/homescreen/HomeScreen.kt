package com.example.presentation.screens.homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screens.lottiescreen.LottieScreen
import com.example.presentation.screens.retrofitscreen.RetrofitScreen
import com.example.presentation.screens.roomscreen.RoomScreen
import com.example.presentation.screens.welcomescreen.WelcomeScreen
import com.example.presentation.utils.BottomRoute
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette
import com.example.presentation.utils.composable.RequestNotificationPermissions

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        ScreenContent(navController)
    }

    RequestNotificationPermissions(
        onPermissionGranted = {
            viewModel.onEvent(HomeContracts.Event.OnNotificationPermissionGranted)
        }
    )
}

@Composable
private fun ScreenContent(bottomBarNavController: NavHostController) {
    val screenRoutes = BottomRoute.all()
    Scaffold(bottomBar = {
        NavigationBar(containerColor = Palette.Yellow) {
            val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            screenRoutes.forEach { screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                NavigationBarItem(
                    modifier = Modifier.semantics { this.testTag = screen.route },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Palette.Yellow
                    ),
                    icon = {
                        val iconRes = if (isSelected) {
                            screen.selectedIconRes
                        } else {
                            screen.unSelectedIconRes
                        }
                        Icon(
                            modifier = Modifier.size(Dimens.BottomNavigationItemSize),
                            painter = painterResource(iconRes),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    },
                    label = { Text(text = screen.route, color = Palette.Peach) },
                    selected = isSelected,
                    onClick = {
                        bottomBarNavController.navigate(screen.route) {
                            popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }) { innerPadding ->
        NavHost(
            bottomBarNavController,
            startDestination = screenRoutes.first().route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomRoute.Lottie.route) { LottieScreen(viewModel = hiltViewModel()) }
            composable(BottomRoute.Welcome.route) { WelcomeScreen(viewModel = hiltViewModel()) }
            composable(BottomRoute.Retrofit.route) { RetrofitScreen(viewModel = hiltViewModel()) }
            composable(BottomRoute.Room.route) { RoomScreen(viewModel = hiltViewModel()) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ScreenContent(bottomBarNavController = rememberNavController())
}
