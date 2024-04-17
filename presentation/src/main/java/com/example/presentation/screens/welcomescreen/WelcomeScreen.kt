package com.example.presentation.screens.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.BuildConfig
import com.example.presentation.R
import com.example.presentation.screens.sharedcomponents.Animation
import com.example.presentation.screens.sharedcomponents.LinkText
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.TextSizes
import com.example.presentation.utils.Weights

@SuppressWarnings("UnusedParameter")
@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel) {
    ScreenContent()
}

@SuppressWarnings("LongMethod")
@Composable
private fun ScreenContent() {
    Row {
        Column(
            modifier = Modifier
                .weight(Weights.HALF)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.WelcomeAnimationSize)
                    .padding(Dimens.BasePadding)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = stringResource(id = R.string.welcome_description_part1),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.BasePadding),
                fontStyle = FontStyle.Italic,
                fontSize = TextSizes.BaseTextSize
            )

            Animation(
                resId = R.raw.paws,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.WelcomeAnimationSize)
            )

            Text(
                text = stringResource(id = R.string.welcome_description_part3),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.BasePadding),
                fontStyle = FontStyle.Italic,
                fontSize = TextSizes.BaseTextSize
            )
        }

        Column(
            modifier = Modifier
                .weight(Weights.HALF)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.WelcomeHeaderPadding),
                fontStyle = FontStyle.Italic,
                fontSize = TextSizes.HeaderTextSize
            )
            Spacer(modifier = Modifier.size(Dimens.WelcomeAnimationSize))

            Image(
                modifier = Modifier
                    .height(Dimens.WelcomeImageSize)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_cat_type2),
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.welcome_description_part2),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(Dimens.BasePadding),
                fontStyle = FontStyle.Italic,
                fontSize = TextSizes.BaseTextSize
            )

            Spacer(modifier = Modifier.size(Dimens.WelcomeAnimationSize))

            Image(
                modifier = Modifier
                    .height(Dimens.WelcomeImageSize)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_cat_type1),
                contentDescription = null
            )

            LinkText(
                text = stringResource(id = R.string.tap_to_read_more),
                link = BuildConfig.README
            )
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
