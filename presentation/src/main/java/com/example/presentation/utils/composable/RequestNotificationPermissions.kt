package com.example.presentation.utils.composable

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun RequestNotificationPermissions(onPermissionGranted: () -> Unit) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

    val requestPermissionState = rememberPermissionState(
        android.Manifest.permission.POST_NOTIFICATIONS
    )

    if (requestPermissionState.status.isGranted) {
        LaunchedEffect(Unit) { onPermissionGranted() }
    } else {
        LaunchedEffect(Unit) {
            requestPermissionState.launchPermissionRequest()
        }
    }
}
