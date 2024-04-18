package com.example.presentation.screens.sharedcomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import com.example.presentation.utils.AnnotatedStringHelper
import com.example.presentation.utils.Dimens

@Composable
fun LinkText(text: String, link: String, modifier: Modifier = Modifier) {
    val tag = AnnotatedStringHelper.TAG_URL
    val formattedText = AnnotatedStringHelper.formatLink(
        text = text,
        link = link,
        tag = tag
    )

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier
            .padding(Dimens.BasePadding)
            .fillMaxWidth(),
        text = formattedText,
        onClick = {
            formattedText
                .getStringAnnotations(tag, it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}
