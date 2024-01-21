package com.maxkor.interonnection.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.maxkor.interonnection.createLog
import com.maxkor.interonnection.domain.usecases.CheckInternetUseCase
import com.maxkor.interonnection.domain.usecases.LoadDataFromServerToDbUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DOWNTIME = 60_000L

@AndroidEntryPoint
class LoadDataService : Service() {

    @Inject
    lateinit var loadDataFromServerToDbUseCase: LoadDataFromServerToDbUseCase

    @Inject
    lateinit var checkInternetUseCase: CheckInternetUseCase

    private val coroutineScopeIO = CoroutineScope(Dispatchers.IO)
    private var shouldLoadData = true

    override fun onCreate() {
        super.onCreate()
        createLog("LoadDataService onCreate")
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.IO).launch {
            while (shouldLoadData) {
                createLog("Service loading data")
                val hasInternetConnection = checkInternetUseCase()
                loadDataFromServerToDbUseCase(hasInternetConnection)
                delay(DOWNTIME)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        coroutineScopeIO.cancel()
        shouldLoadData = false
        super.onDestroy()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, LoadDataService::class.java)
    }

}