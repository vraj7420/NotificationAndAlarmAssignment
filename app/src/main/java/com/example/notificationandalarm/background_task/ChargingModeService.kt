package com.example.notificationandalarm.background_task

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class ChargingModeService : Service() {
    private lateinit var chargingModeReceiver: ChargingModeReceiver

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        chargingModeReceiver = ChargingModeReceiver()
        registerReceiver(chargingModeReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        return super.onStartCommand(intent, flags, startId)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        unregisterReceiver(chargingModeReceiver)

    }


}