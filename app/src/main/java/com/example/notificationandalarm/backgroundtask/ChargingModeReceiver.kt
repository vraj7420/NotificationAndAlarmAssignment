package com.example.notificationandalarm.backgroundtask

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.BatteryManager
import android.os.Build
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class ChargingModeReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "Charging Mode"
    private val description = "Set notification"

    @SuppressLint("UnspecifiedImmutableFlag", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(ctx: Context?, intent: Intent?) {

        val batteryStatus = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra("Alarm Title", ctx!!.getString(R.string.charge_mode_alarm))
        notificationIntent.putExtra(
            "Alarm Short Description",
            ctx.getString(R.string.charge_mode_alarm_short_description)
        )
        notificationIntent.putExtra(
            "Alarm Long Description",
            ctx.getString(R.string.charge_mode_alarm_long_description)
        )

        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(ctx, channelId)
                    .setSmallIcon(R.drawable.ic_battery_charging)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.battery_charging))
                    .setContentText(ctx.getString(R.string.charge_mode_alarm_short_description))


            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_battery_charging)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.battery_charging))
                    .setContentText(ctx.getString(R.string.charge_mode_alarm_short_description))


            }
            notificationManager.notify(1234, builder.build())
        }

        if (batteryStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)



                builder = Notification.Builder(ctx, channelId)
                    .setSmallIcon(R.drawable.ic_battery_not_charging)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.battery_not_charging))
                    .setContentText(ctx.getString(R.string.charge_mode_alarm_short_description))


            } else {
                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_battery_not_charging)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.battery_not_charging))
                    .setContentText(ctx.getString(R.string.charge_mode_alarm_short_description))


            }
        }
        notificationManager.notify(1234, builder.build())

    }
}




