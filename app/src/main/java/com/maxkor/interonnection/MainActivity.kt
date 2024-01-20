package com.maxkor.interonnection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.maxkor.interonnection.service.LoadDataService
import com.maxkor.interonnection.ui.screens.MainScreen
import com.maxkor.interonnection.ui.theme.InterСonnectionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterСonnectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }

    override fun onResume() {
        createLog("onResume")
        super.onResume()
        startService(LoadDataService.newIntent(this))
        createLog("startService")
    }

    override fun onPause() {
        createLog("onPause")
        super.onPause()
        stopService(LoadDataService.newIntent(this))
        createLog("stopService")
    }

}

