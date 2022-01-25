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
import com.example.notificationandalarm.constant.ConstantString
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class AfterSomeTimeAlarmSetReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder


    @SuppressLint("UnspecifiedImmutableFlag", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(ctx: Context?, intent: Intent?) {

        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra(
            ConstantString.alarmTitle,
            ctx!!.getString(R.string.after_certain_amount_time)
        )
        notificationIntent.putExtra(
            ConstantString.alarmShortDescription,
            ctx.getString(R.string.set_alarm_after_some_time_short_description)
        )
        notificationIntent.putExtra(
            ConstantString.alarmLongDescription,
            ctx.getString(R.string.set_alarm_after_some_time_long_description)
        )

        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)


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
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentIntent(pendingIntent)
                .setContentTitle(ctx.getString(R.string.after_some_alarm_set))
                .setContentText(ctx.getString(R.string.set_alarm_after_some_time_short_description))
                .setAutoCancel(true)
        } else {

            builder = Notification.Builder(ctx)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentIntent(pendingIntent)
                .setContentTitle(ctx.getString(R.string.after_some_alarm_set))
                .setContentText(ctx.getString(R.string.set_alarm_after_some_time_short_description))
                .setAutoCancel(true)


        }
        notificationManager.notify(1234, builder.build())


    }

}