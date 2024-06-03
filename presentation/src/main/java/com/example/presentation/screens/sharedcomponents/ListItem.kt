package com.example.presentation.screens.sharedcomponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.screens.retrofitscreen.entity.CatUi

@Composable
internal fun ListItem(
    catUi: CatUi,
    isFavorite: Boolean = true,
    onAddToFavorite: (CatUi) -> Unit = {},
    onDeleteFromFavorite: (CatUi) -> Unit,
) {
    Box(modifier = Modifier.semantics { this.testTag = "List item: ${catUi.id}" }) {
        AsyncImage(
            model = catUi.image,
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(R.drawable.ic_paws),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
        FavoriteIcon(
            catUi = catUi,
            isFavorite = isFavorite,
            onAddToFavorite = onAddToFavorite,
            onDeleteFromFavorite = onDeleteFromFavorite,
            modifier = Modifier.semantics { this.testTag = "Favorite icon: ${catUi.id}" }
        )
    }
}
