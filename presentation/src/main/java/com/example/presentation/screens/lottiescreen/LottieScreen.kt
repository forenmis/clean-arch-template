package com.example.presentation.screens.lottiescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.R
import com.example.presentation.screens.sharedcomponents.Animation
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette
import com.example.presentation.utils.composable.ComposeAdsView

@SuppressWarnings("UnusedParameter")
@Composable
fun LottieScreen(viewModel: LottieViewModel) {
    ScreenContent()
}

@SuppressWarnings("TooGenericExceptionThrown")
@Composable
private fun ScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Palette.Blue),
        contentAlignment = Alignment.Center
    ) {
        Animation(resId = R.raw.cat)

        ComposeAdsView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )

        // Button for test crashlytics
        Button(
            onClick = { throw RuntimeException("Test Crash") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(Dimens.BaseDoublePadding)
        ) {
            Text(text = "Crash")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent()
    }
}
