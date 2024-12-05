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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screens.retrofitscreen.RetrofitScreen
import com.example.presentation.screens.roomscreen.RoomScreen
import com.example.presentation.screens.selectvideoscreen.SelectVideoScreen
import com.example.presentation.screens.welcomescreen.WelcomeScreen
import com.example.presentation.utils.BottomRoute
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette

@Composable
fun HomeScreen(bottomBarNavController: NavHostController, onNavigateToPlayer: (String) -> Unit) {
    MaterialTheme {
        ScreenContent(
            bottomBarNavController = bottomBarNavController,
            onNavigateToPlayer = onNavigateToPlayer
        )
    }
}

@Composable
private fun ScreenContent(
    bottomBarNavController: NavHostController,
    onNavigateToPlayer: (String) -> Unit,
) {
    val screenRoutes = BottomRoute.all()
    var currentDestination by remember { mutableStateOf(screenRoutes.first()) }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Palette.Yellow) {
                screenRoutes.forEach { screen ->
                    val isSelected = currentDestination == screen
                    NavigationBarItem(
                        modifier = Modifier.semantics { this.testTag = screen.screenName },
                        colors = NavigationBarItemDefaults.colors(indicatorColor = Palette.Yellow),
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
                        label = {
                            Text(
                                text = screen.screenName,
                                color = Palette.Peach
                            )
                        },
                        selected = isSelected,
                        onClick = { currentDestination = screen }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomBarNavController,
            startDestination = currentDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<BottomRoute.SelectVideo> {
                SelectVideoScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToPlayer = onNavigateToPlayer
                )
            }
            composable<BottomRoute.Welcome> { WelcomeScreen(viewModel = hiltViewModel()) }
            composable<BottomRoute.Retrofit> { RetrofitScreen(viewModel = hiltViewModel()) }
            composable<BottomRoute.Room> { RoomScreen(viewModel = hiltViewModel()) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    ScreenContent(
        bottomBarNavController = rememberNavController(),
        onNavigateToPlayer = {}
    )
}
