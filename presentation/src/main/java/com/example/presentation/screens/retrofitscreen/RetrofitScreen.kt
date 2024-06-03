@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.screens.retrofitscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.screens.retrofitscreen.entity.CatUi
import com.example.presentation.screens.sharedcomponents.ListItem
import com.example.presentation.utils.Dimens
import com.example.presentation.utils.Palette
import com.example.presentation.utils.PreviewParams

@Composable
fun RetrofitScreen(viewModel: RetrofitViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ScreenContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun ScreenContent(
    state: RetrofitContracts.State,
    onEvent: (RetrofitContracts.Event) -> Unit,
) {
    val refreshState = rememberPullToRefreshState()

    if (refreshState.isRefreshing) {
        LaunchedEffect(true) {
            onEvent(RetrofitContracts.Event.OnLoad)
        }
    }

    LaunchedEffect(state.isLoading) {
        if (state.isLoading) refreshState.startRefresh() else refreshState.endRefresh()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Palette.Pink)
            .nestedScroll(refreshState.nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .semantics { this.testTag = "List images" },
            verticalArrangement = Arrangement.spacedBy(Dimens.BasePadding)
        ) {
            items(state.images) { catUi ->
                ListItem(
                    catUi = catUi,
                    isFavorite = catUi.isFavorite,
                    onAddToFavorite = { onEvent(RetrofitContracts.Event.OnAddToFavorite(it)) },
                    onDeleteFromFavorite = {
                        onEvent(
                            RetrofitContracts.Event.OnDeleteFromFavorite(it.id)
                        )
                    }
                )
            }
        }

        PullToRefreshContainer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { this.testTag = "Refresh images" },
            state = refreshState
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = RetrofitContracts.State(),
            onEvent = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ItemPreview() {
    MaterialTheme {
        ListItem(
            isFavorite = true,
            catUi = CatUi(
                id = "q",
                image = PreviewParams.CAT_IMAGE_URL
            ),
            onDeleteFromFavorite = {},
            onAddToFavorite = {}
        )
    }
}
