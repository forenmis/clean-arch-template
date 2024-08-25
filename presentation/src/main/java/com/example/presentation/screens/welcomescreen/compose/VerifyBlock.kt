@file:Suppress("MagicNumber")
package com.example.presentation.screens.welcomescreen.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette

@Composable
internal fun VerifyBlock(
    isEnabledVerify: Boolean,
    sendPhoneNumber: () -> Unit,
    sendVerifyCode: () -> Unit,
    phoneNumber: String,
    onPhoneNumberChanged: (String) -> Unit,
    isPhoneNumberValid: Boolean,
    verificationCode: String,
    verificationCodeSent: Boolean,
    onVerifyCodeChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        if (!verificationCodeSent) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = onPhoneNumberChanged,
                label = { Text("Phone number with code of country") },
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.BaseDoublePadding),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = !isPhoneNumberValid
            )
            VerifyButton(onClick = sendPhoneNumber, isEnabled = isEnabledVerify)
        } else {
            VerificationCode(
                code = verificationCode,
                onCodeChange = onVerifyCodeChanged
            )

            VerifyButton(onClick = sendVerifyCode)
        }
    }
}

@Composable
private fun VerificationCode(
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        modifier = modifier,
        value = code,
        onValueChange = onCodeChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        decorationBox = {
            val numbers = code.toCharArray()
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(6) { index ->
                    Box(
                        modifier = Modifier
                            .padding(Dimens.BasePadding)
                            .background(
                                color = Palette.LightPeach,
                                shape = RoundedCornerShape(Dimens.BasePadding)
                            )
                            .size(Dimens.BottomNavigationItemSize),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = numbers.getOrNull(index)?.toString() ?: "")
                    }
                }
            }
        }
    )
}

@Composable
private fun VerifyButton(onClick: () -> Unit, isEnabled: Boolean = true) {
    Button(
        enabled = isEnabled,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.BasePadding),
        colors = ButtonDefaults.buttonColors(
            containerColor = Palette.VioletDark
        )
    ) {
        Text(text = "Verify")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun VerifyBlockPreview() {
    MaterialTheme {
        VerifyBlock(
            isEnabledVerify = true,
            sendPhoneNumber = {},
            sendVerifyCode = {},
            phoneNumber = "+380",
            onPhoneNumberChanged = {},
            isPhoneNumberValid = true,
            verificationCode = "12345",
            verificationCodeSent = true,
            onVerifyCodeChanged = {}
        )
    }
}
