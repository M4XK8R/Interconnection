package com.maxkor.interonnection.helpers

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.maxkor.interonnection.domain.helpers.ActivityResultHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ActivityResultHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ActivityResultHelper {

    override fun <V> checkPermission(
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