package com.example.presentation.screens.shared_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.retrofit_screen.entity.CatUi
import com.example.presentation.utils.Palette

@Composable
internal fun BoxScope.FavoriteIcon(
    catUi: CatUi,
    isFavorite: Boolean = false,
    onAddToFavorite: (CatUi) -> Unit,
    onDeleteFromFavorite: (CatUi) -> Unit,
) {

    val onClick = if (isFavorite) onDeleteFromFavorite else onAddToFavorite
    val icon = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_unfavorite

    Icon(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick(catUi) }
            .align(Alignment.TopEnd)
            .size(24.dp),
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = Palette.Yellow)
}