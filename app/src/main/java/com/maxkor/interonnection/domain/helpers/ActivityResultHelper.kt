package com.maxkor.interonnection.domain.helpers

import androidx.activity.compose.ManagedActivityResultLauncher

interface ActivityResultHelper {

    fun <V> checkPermission(
        launcher: ManagedActivityResultLauncher<String, V>,
        noPermissionCase: () -> Unit,
        defaultCase: () -> Unit,
    )
}