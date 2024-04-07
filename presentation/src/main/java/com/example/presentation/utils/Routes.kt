package com.example.presentation.utils


object Routes {
    const val HOME = "home"
}

sealed class BottomRoute(val route: String) {
    data object Green : BottomRoute("green")
    data object Violet : BottomRoute("violet")
    data object Pink : BottomRoute("pink")
    data object Blue : BottomRoute("blue")

    companion object {
        fun all(): List<BottomRoute> = listOf(Green, Violet, Pink, Blue)
    }
}
