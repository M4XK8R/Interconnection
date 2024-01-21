package com.maxkor.interonnection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.maxkor.interonnection.broadcast.NotyReceiver
import com.maxkor.interonnection.service.LoadDataService
import com.maxkor.interonnection.ui.screens.base.MainScreen
import com.maxkor.interonnection.ui.theme.InterСonnectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var itemId = NotyReceiver.ID_DEFAULT_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let {
            if (it.action == NotyReceiver.ACTION_SHOW_REQUIRED_SCREEN) {
                itemId = it.getStringExtra(NotyReceiver.ID_PARAM)
                    ?: NotyReceiver.ID_DEFAULT_VALUE
            }
        }

        setContent {
            InterСonnectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(itemId)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startService(LoadDataService.newIntent(this))
    }

    override fun onPause() {
        super.onPause()
        stopService(LoadDataService.newIntent(this))
    }
}

