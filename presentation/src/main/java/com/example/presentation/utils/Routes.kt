package com.example.presentation.utils

import com.example.presentation.R
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data class Player(val uri: String)

@Serializable
sealed class BottomRoute(
    val screenName: String,
    val selectedIconRes: Int = R.drawable.ic_open_box,
    val unSelectedIconRes: Int = R.drawable.ic_closed_box,
) {
    @Serializable
    data object Welcome : BottomRoute("Welcome")

    @Serializable
    data object Room : BottomRoute("Room")

    @Serializable
    data object Retrofit : BottomRoute("Retrofit")

    @Serializable
    data object SelectVideo : BottomRoute("Select Video")

    companion object {
        fun all(): List<BottomRoute> = listOf(Welcome, Room, Retrofit, SelectVideo)
    }
}
