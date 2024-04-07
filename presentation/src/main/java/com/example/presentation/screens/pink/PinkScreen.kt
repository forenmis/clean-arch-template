@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.presentation.screens.pink

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.presentation.R
import com.example.presentation.utils.Palette

@Composable
fun PinkScreen(viewModel: PinkViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()



    LaunchedEffect(Unit) { viewModel.onEvent(PinkContracts.Event.OnLoad) }

    ScreenContent(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun ScreenContent(state: PinkContracts.State, onEvent: (PinkContracts.Event) -> Unit) {
    val refreshState = rememberPullToRefreshState()

    if (refreshState.isRefreshing) {
        LaunchedEffect(true) {
            onEvent(PinkContracts.Event.OnLoad)
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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.images) { catUi ->
                ListItem(image = catUi.image)
            }
        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = refreshState
        )
    }
}

@Composable
private fun ListItem(image: String) {
    AsyncImage(
        model = image,
        contentScale = ContentScale.FillWidth,
        placeholder = painterResource(R.drawable.ic_paws),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScreenPreview() {
    MaterialTheme {
        ScreenContent(
            state = PinkContracts.State(),
            onEvent = {}
        )
    }
}
