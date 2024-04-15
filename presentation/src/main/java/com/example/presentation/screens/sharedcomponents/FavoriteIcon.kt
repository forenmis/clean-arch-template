package com.example.presentation.screens.sharedcomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.presentation.R
import com.example.presentation.screens.retrofitscreen.entity.CatUi
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette

@JvmOverloads
@Composable
internal fun BoxScope.FavoriteIcon(
    catUi: CatUi,
    onAddToFavorite: (CatUi) -> Unit,
    onDeleteFromFavorite: (CatUi) -> Unit,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
) {
    val onClick = if (isFavorite) onDeleteFromFavorite else onAddToFavorite
    val icon = if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_unfavorite

    Icon(
        modifier = modifier
            .padding(Dimens.BaseDoublePadding)
            .clickable { onClick(catUi) }
            .align(Alignment.TopEnd)
            .size(Dimens.BaseTriplePadding),
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = Palette.Yellow)
}
