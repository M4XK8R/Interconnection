package com.maxkor.interonnection.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.interonnection.ui.theme.InterСonnectionTheme

class PreviewProvider(private val content: @Composable () -> Unit) {

    @Composable
    fun Run() {
        InterСonnectionTheme {
            Surface(Modifier.fillMaxSize()) {
                content()
            }
        }
    }

    @Composable
    fun Default() {
        InterСonnectionTheme {
            Surface {
                content()
            }
        }
    }

}