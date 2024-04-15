package com.example.presentation.screens.lottiescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.R
import com.example.presentation.screens.sharedcomponents.Animation
import com.example.presentation.utils.Palette

@SuppressWarnings("UnusedParameter")
@Composable
fun LottieScreen(viewModel: LottieViewModel) {

    ScreenContent()
}

@Composable
private fun ScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Palette.Blue),
        contentAlignment = Alignment.Center
    ) {
        Animation(resId = R.raw.cat)
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent()
    }
}
