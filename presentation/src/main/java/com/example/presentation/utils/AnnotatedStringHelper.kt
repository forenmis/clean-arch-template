package com.example.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

object AnnotatedStringHelper {

    const val TAG_URL = "URL"

    fun formatLink(text: String, link: String, tag: String): AnnotatedString {
        return buildAnnotatedString {
            val startIndex = 0
            val endIndex = text.length
            append(text)
            addStyle(
                style = SpanStyle(
                    color = Palette.VioletDark,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Italic
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = tag,
                annotation = link,
                start = startIndex,
                end = endIndex
            )
        }
    }
}
