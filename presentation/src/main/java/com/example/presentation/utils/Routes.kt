package com.example.presentation.utils

import com.example.presentation.R
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data class Player(val uri: String)

sealed class BottomRoute(
    val route: String,
    val selectedIconRes: Int = R.drawable.ic_open_box,
    val unSelectedIconRes: Int = R.drawable.ic_closed_box,
) {
    data object Welcome : BottomRoute("welcome")
    data object Room : BottomRoute("room")
    data object Retrofit : BottomRoute("retrofit")
    data object SelectVideo : BottomRoute("select_video")

    companion object {
        fun all(): List<BottomRoute> = listOf(Welcome, Room, Retrofit, SelectVideo)
    }
}
