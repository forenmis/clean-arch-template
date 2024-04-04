package com.example.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.core.Palette

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToGreen: () -> Unit,
    onNavigateToPink: () -> Unit,
    onNavigateToBlue: () -> Unit,
    onNavigateToViolet: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.load()
    }

    ScreenContent(
        onNavigateToGreen = onNavigateToGreen,
        onNavigateToPink = onNavigateToPink,
        onNavigateToBlue = onNavigateToBlue,
        onNavigateToViolet = onNavigateToViolet
    )
}

@Composable
private fun ScreenContent(
    onNavigateToGreen: () -> Unit,
    onNavigateToPink: () -> Unit,
    onNavigateToBlue: () -> Unit,
    onNavigateToViolet: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Text")
        Button(
            onClick = onNavigateToGreen,
            colors = ButtonDefaults.buttonColors(containerColor = Palette.Green)
        ) {
            Text(text = "Green")
        }
        Button(
            onClick = onNavigateToPink,
            colors = ButtonDefaults.buttonColors(containerColor = Palette.Pink)
        ) {
            Text(text = "Pink")
        }
        Button(
            onClick = onNavigateToViolet,
            colors = ButtonDefaults.buttonColors(containerColor = Palette.Violet)
        ) {
            Text(text = "Violet")
        }
        Button(
            onClick = onNavigateToBlue,
            colors = ButtonDefaults.buttonColors(containerColor = Palette.Blue)
        ) {
            Text(text = "Blue")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
        ScreenContent(
            onNavigateToGreen = {},
            onNavigateToViolet = {},
            onNavigateToBlue = {},
            onNavigateToPink = {}
        )
}
