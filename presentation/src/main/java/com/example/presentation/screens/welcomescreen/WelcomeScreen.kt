package com.example.presentation.screens.welcomescreen

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.screens.welcomescreen.compose.VerifyBlock

@SuppressWarnings("UnusedParameter")
@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ScreenContent(state = state, onEvent = viewModel::onEvent)
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                WelcomeContracts.Effect.OnVerificationCompleted -> Toast.makeText(
                    context,
                    "Completed",
                    Toast.LENGTH_SHORT
                ).show()

                WelcomeContracts.Effect.OnVerificationFailed -> Toast.makeText(
                    context,
                    "Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@SuppressWarnings("LongMethod")
@Composable
private fun ScreenContent(
    state: WelcomeContracts.State,
    onEvent: (WelcomeContracts.Event) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
//        Row(modifier = Modifier.semantics { this.testTag = "Welcome screen content" }) {
//            Column(
//                modifier = Modifier
//                    .weight(Weights.HALF)
//                    .fillMaxSize()
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.jetpack),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(Dimens.WelcomeAnimationSize)
//                        .padding(Dimens.BasePadding)
//                        .align(Alignment.CenterHorizontally)
//                        .semantics {
//                            this.testTag = "Welcome image"
//                        }
//                )
//
//                Text(
//                    text = stringResource(id = R.string.welcome_description_part1),
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(Dimens.BasePadding)
//                        .semantics { this.testTag = "Welcome text part 1" },
//                    fontStyle = FontStyle.Italic,
//                    fontSize = TextSizes.BaseTextSize
//                )
//
//                Animation(
//                    resId = R.raw.paws,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(Dimens.WelcomeAnimationSize)
//                )
//
//                Text(
//                    text = stringResource(id = R.string.welcome_description_part3),
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(Dimens.BasePadding)
//                        .semantics { this.testTag = "Welcome text part 2" },
//                    fontStyle = FontStyle.Italic,
//                    fontSize = TextSizes.BaseTextSize
//                )
//            }
//
//            Column(
//                modifier = Modifier
//                    .weight(Weights.HALF)
//                    .fillMaxSize()
//            ) {
//                Text(
//                    text = stringResource(id = R.string.welcome),
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(Dimens.WelcomeHeaderPadding)
//                        .semantics { this.testTag = "Welcome text" },
//                    fontStyle = FontStyle.Italic,
//                    fontSize = TextSizes.HeaderTextSize
//                )
//                Spacer(modifier = Modifier.size(Dimens.WelcomeAnimationSize))
//
//                Image(
//                    modifier = Modifier
//                        .height(Dimens.WelcomeImageSize)
//                        .fillMaxWidth()
//                        .semantics { this.testTag = "Image cat 1" },
//                    painter = painterResource(id = R.drawable.ic_cat_type2),
//                    contentDescription = null
//                )
//
//                Text(
//                    text = stringResource(id = R.string.welcome_description_part2),
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .padding(Dimens.BasePadding)
//                        .semantics { this.testTag = "Welcome text part 3" },
//                    fontStyle = FontStyle.Italic,
//                    fontSize = TextSizes.BaseTextSize
//                )
//
//                Spacer(modifier = Modifier.size(Dimens.WelcomeAnimationSize))
//
//                Image(
//                    modifier = Modifier
//                        .height(Dimens.WelcomeImageSize)
//                        .fillMaxWidth()
//                        .semantics { this.testTag = "Image cat 2" },
//                    painter = painterResource(id = R.drawable.ic_cat_type1),
//                    contentDescription = null
//                )
//
//                LinkText(
//                    modifier = Modifier.semantics { this.testTag = "Welcome link" },
//                    text = stringResource(id = R.string.tap_to_read_more),
//                    link = BuildConfig.README
//                )
//            }
//        }

        val activity = LocalContext.current as Activity
        VerifyBlock(
            modifier = Modifier.padding(top = 64.dp),
            isEnabledVerify = state.isEnabledVerify,
            sendPhoneNumber = { onEvent(WelcomeContracts.Event.OnSendPhone(activity)) },
            sendVerifyCode = { onEvent(WelcomeContracts.Event.OnSendVerifyCode) },
            phoneNumber = state.phoneNumber,
            onPhoneNumberChanged = { onEvent(WelcomeContracts.Event.OnPhoneNumberChanged(it)) },
            isPhoneNumberValid = state.isPhoneNumberValid,
            verificationCode = state.verificationCode,
            verificationCodeSent = state.verificationCodeSent,
            onVerifyCodeChanged = {
                onEvent(WelcomeContracts.Event.OnVerifyCodeChanged(it))
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent(WelcomeContracts.State()) {}
    }
}
