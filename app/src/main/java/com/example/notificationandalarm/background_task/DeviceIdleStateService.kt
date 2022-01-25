package com.example.notificationandalarm.background_task

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class DeviceIdleStateService : Service() {
    private lateinit var deviceIdleStateService: DeviceIdleStateReceiver

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        deviceIdleStateService = DeviceIdleStateReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        registerReceiver(deviceIdleStateService, intentFilter)
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        unregisterReceiver(deviceIdleStateService)

    }


}