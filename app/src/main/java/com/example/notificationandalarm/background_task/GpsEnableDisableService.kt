package com.example.notificationandalarm.background_task

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.IBinder

class GpsEnableDisableService : Service() {
    private lateinit var gpsEnableDisableReceiver: GpsEnableDisableReceiver

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        gpsEnableDisableReceiver = GpsEnableDisableReceiver()
        registerReceiver(
            gpsEnableDisableReceiver,
            IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        )
        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        unregisterReceiver(gpsEnableDisableReceiver)
    }


}