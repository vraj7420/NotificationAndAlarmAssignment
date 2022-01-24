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
import android.os.Build
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class RepeatingAlarmReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "Repeating Alarm Time"
    private val description = "Set notification"


    @SuppressLint("UnspecifiedImmutableFlag", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra(
            "Alarm Title",
            ctx!!.getString(R.string.after_certain_amount_time)
        )
        notificationIntent.putExtra(
            "Alarm Short Description",
            ctx.getString(R.string.set_alarm_after_some_time_short_description)
        )
        notificationIntent.putExtra(
            "Alarm Long Description",
            ctx.getString(R.string.set_alarm_after_some_time_long_description)
        )

        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)


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
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentIntent(pendingIntent)
                .setContentText(ctx.getString(R.string.repeated_alarm_set))
                .setContentText(ctx.getString(R.string.set_alarm_after_some_time_short_description))
                .setAutoCancel(true)
        } else {

            builder = Notification.Builder(ctx)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentIntent(pendingIntent)
                .setContentText(ctx.getString(R.string.repeated_alarm_set))
                .setContentText(ctx.getString(R.string.set_alarm_after_some_time_short_description))
                .setAutoCancel(true)


        }
        notificationManager.notify(1234, builder.build())


    }
}