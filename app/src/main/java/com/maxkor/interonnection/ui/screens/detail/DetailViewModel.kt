package com.maxkor.interonnection.ui.screens.detail

import android.app.PendingIntent
import android.content.Context
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkor.interonnection.domain.models.DataModel
import com.maxkor.interonnection.domain.usecases.CheckPermissionUseCase
import com.maxkor.interonnection.domain.usecases.CreateAlarmUseCase
import com.maxkor.interonnection.domain.usecases.GetElementUseCase
import com.maxkor.interonnection.domain.usecases.ImageShareUseCase
import com.maxkor.interonnection.domain.usecases.SavePictureUseCase
import com.maxkor.interonnection.domain.usecases.ShowNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getElementUseCase: GetElementUseCase,
    private val createAlarmUseCase: CreateAlarmUseCase,
    private val showNotificationUseCase: ShowNotificationUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val imageShareUseCase: ImageShareUseCase,
    private val savePictureUseCase: SavePictureUseCase,
) : ViewModel() {

    private val _currentElement = mutableStateOf(DataModel.initial)
    val currentElement: State<DataModel> = _currentElement

    fun saveImage(
        context: Context,
        downloadUrlOfImage: String,
        filename: String
    ) {
        savePictureUseCase(context, downloadUrlOfImage, filename)
    }

    fun shareImage(imageUrl: String, context: Context) {
        imageShareUseCase(imageUrl, context)
    }

    fun <V> checkPermission(
        launcher: ManagedActivityResultLauncher<String, V>,
        noPermissionCase: () -> Unit,
        defaultCase: () -> Unit,
    ) {
        checkPermissionUseCase(launcher, noPermissionCase, defaultCase)
    }

    fun showNotification(contentText: String?, contentIntent: PendingIntent?) {
        showNotificationUseCase(contentText, contentIntent)
    }


    fun createAlarm(time: Long, extraText: String, itemId: String) {
        createAlarmUseCase(time, extraText, itemId)
    }

    fun getElement(dataModelId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            _currentElement.value = getElementUseCase(dataModelId)
        }
    }
}