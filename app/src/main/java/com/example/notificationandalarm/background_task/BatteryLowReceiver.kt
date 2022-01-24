package com.example.notificationandalarm.background_task

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class BatteryLowReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "Battery Low"
    private val description = "Set notification"

    @SuppressLint("UnspecifiedImmutableFlag", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val batteryStatus = intent?.getIntExtra("level", 0)
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra("Alarm Title", ctx!!.getString(R.string.battery_low_alarm))
        notificationIntent.putExtra(
            "Alarm Short Description",
            ctx.getString(R.string.battery_low_short_description)
        )
        notificationIntent.putExtra(
            "Alarm Long Description",
            ctx.getString(R.string.battery_low_long_description)
        )


        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (batteryStatus != null) {
            if (batteryStatus < 15) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel =
                        NotificationChannel(
                            channelId,
                            description,
                            NotificationManager.IMPORTANCE_HIGH
                        )
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    notificationChannel.enableVibration(false)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(ctx, channelId)
                        .setSmallIcon(R.drawable.ic_battery_low)
                        .setContentIntent(pendingIntent)
                        .setContentText(ctx.getString(R.string.battery_low_short_description))
                        .setContentTitle(ctx.getString(R.string.battery_low))
                } else {

                    builder = Notification.Builder(ctx)
                        .setSmallIcon(R.drawable.ic_battery_low)
                        .setContentIntent(pendingIntent)
                        .setContentText(ctx.getString(R.string.battery_low_short_description))
                        .setContentTitle(ctx.getString(R.string.battery_low))

                }
                notificationManager.notify(1234, builder.build())
            }
        }

    }
}