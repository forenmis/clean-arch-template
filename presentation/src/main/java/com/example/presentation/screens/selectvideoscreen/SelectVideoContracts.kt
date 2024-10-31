package com.example.presentation.screens.selectvideoscreen

import com.example.presentation.BuildConfig
import com.example.presentation.core.BaseEffect
import com.example.presentation.core.BaseEvent
import com.example.presentation.core.BaseState

class SelectVideoContracts {
    data class State(
        val items: List<String> = listOf(
            BuildConfig.EXAMPLE_VIDEO_URI,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_1,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_2,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_3,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_4,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_5,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_6,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_8,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_9,
            BuildConfig.EXAMPLE_TEST_VIDEO_URI_10
        ),
    ) : BaseState

    sealed interface Event : BaseEvent {
        data class OnItemClick(val uri: String) : Event
    }

    sealed interface Effect : BaseEffect {
        data class OpenPlayer(val uri: String) : Effect
    }
}
