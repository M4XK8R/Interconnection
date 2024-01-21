package com.maxkor.interonnection.domain.usecases

import androidx.activity.compose.ManagedActivityResultLauncher
import com.maxkor.interonnection.domain.helpers.ActivityResultHelper
import javax.inject.Inject

class CheckPermissionUseCase @Inject constructor(
    private val activityResultHelper: ActivityResultHelper
) {
    operator fun <V> invoke(
        launcher: ManagedActivityResultLauncher<String, V>,
        noPermissionCase: () -> Unit,
        defaultCase: () -> Unit
    ) {
        activityResultHelper.checkPermission(launcher, noPermissionCase, defaultCase)
    }
}