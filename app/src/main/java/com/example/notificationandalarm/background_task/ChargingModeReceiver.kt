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
import android.os.BatteryManager
import android.os.Build
import com.example.notificationandalarm.R
import com.example.notificationandalarm.constant.ConstantString
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class ChargingModeReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private lateinit var pendingIntent: PendingIntent

    @SuppressLint("UnsafeProtectedBroadcastReceiver", "UnspecifiedImmutableFlag")
    override fun onReceive(ctx: Context?, intent: Intent?) {

        val batteryStatus = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra(
            ConstantString.alarmTitle,
            ctx!!.getString(R.string.charge_mode_alarm)
        )
        notificationIntent.putExtra(
            ConstantString.alarmShortDescription,
            ctx.getString(R.string.charge_mode_alarm_short_description)
        )
        notificationIntent.putExtra(
            ConstantString.alarmLongDescription,
            ctx.getString(R.string.charge_mode_alarm_long_description)
        )

        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        when (batteryStatus) {

            BatteryManager.BATTERY_STATUS_CHARGING -> {
                notificationCreate(
                    ctx,
                    R.drawable.ic_battery_charging,
                    ctx.getString(R.string.battery_charging),
                    ctx.getString(R.string.battery_low_short_description)
                )
            }

            BatteryManager.BATTERY_STATUS_DISCHARGING -> {
                notificationCreate(
                    ctx,
                    R.drawable.ic_battery_not_charging,
                    ctx.getString(R.string.battery_not_charging),
                    ctx.getString(R.string.battery_low_short_description)
                )
            }

        }
    }


    private fun notificationCreate(
        ctx: Context?,
        smallIcon: Int,
        title: String,
        shortDescription: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(
                    ConstantString.channelId,
                    ConstantString.description,
                    NotificationManager.IMPORTANCE_HIGH
                )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(ctx, ConstantString.channelId)
                .setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(shortDescription)


        } else {

            builder = Notification.Builder(ctx)
                .setSmallIcon(smallIcon)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(shortDescription)


        }
        notificationManager.notify(1234, builder.build())


    }


}

