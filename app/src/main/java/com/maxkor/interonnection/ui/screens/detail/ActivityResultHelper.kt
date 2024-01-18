package com.maxkor.interonnection.ui.screens.detail

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

object ActivityResultHelper {

    fun <V> checkPermission(
        context: Context,
        launcher: ManagedActivityResultLauncher<String, V>,
        noPermissionCase: () -> Unit,
        defaultCase: () -> Unit,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val currentPermissionStatus = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            )
            if (currentPermissionStatus != PermissionChecker.PERMISSION_GRANTED) {
                noPermissionCase()
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                defaultCase()
            }
        } else {
            defaultCase()
        }
    }
}