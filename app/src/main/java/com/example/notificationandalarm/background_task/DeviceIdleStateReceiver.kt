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
import android.util.Log
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class DeviceIdleStateReceiver : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "Device Idle State"
    private val description = "Set notification"


    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        Log.d("On Receive", "On Receive ")
        notificationIntent.putExtra("Alarm Title", ctx!!.getString(R.string.device_idle_alarm))
        notificationIntent.putExtra(
            "Alarm Short Description",
            ctx.getString(R.string.device_idle_alarm_short_description)
        )
        notificationIntent.putExtra(
            "Alarm Long Description",
            ctx.getString(R.string.device_idle_alarm_long_description)
        )

        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = PendingIntent.getActivity(
            ctx,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        Log.d("reci", "rec")
        if (intent?.action.equals(Intent.ACTION_SCREEN_OFF)) {

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
                    .setSmallIcon(R.drawable.ic_device_idle_state)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.device_idle))
                    .setContentText(ctx.getString(R.string.device_idle_alarm_short_description))

            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_device_idle_state)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.device_idle))
                    .setContentText(ctx.getString(R.string.device_idle_alarm_short_description))


            }
            notificationManager.notify(1234, builder.build())


        }
        if (intent?.action.equals(Intent.ACTION_SCREEN_ON)) {

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
                    .setSmallIcon(R.drawable.ic_device_not_idle_state)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.device_not_idle))
                    .setContentText(ctx.getString(R.string.device_idle_alarm_short_description))


            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_device_not_idle_state)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.device_not_idle))
                    .setContentText(ctx.getString(R.string.device_idle_alarm_short_description))


            }
            notificationManager.notify(1234, builder.build())


        }


    }

}

