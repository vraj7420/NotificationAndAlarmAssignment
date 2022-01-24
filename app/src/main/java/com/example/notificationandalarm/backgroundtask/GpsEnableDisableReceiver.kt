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
import android.location.LocationManager
import android.os.Build
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity

class GpsEnableDisableReceiver : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "Gps Enable Disable"
    private val description = "Set notification"


    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onReceive(ctx: Context?, intent: Intent?) {

        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra("Alarm Title", ctx!!.getString(R.string.gps_alarm))
        notificationIntent.putExtra(
            "Alarm Short Description",
            ctx.getString(R.string.gps_state_change_alarm_short_description)
        )
        notificationIntent.putExtra(
            "Alarm Long Description",
            ctx.getString(R.string.gps_state_change_alarm_long_description)
        )

        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val manager: LocationManager =
            ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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
                    .setSmallIcon(R.drawable.ic_gps_enable)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.gps_enable))
                    .setContentText(ctx.getString(R.string.gps_state_change_alarm_short_description))
            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_gps_enable)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.gps_enable))
                    .setContentText(ctx.getString(R.string.gps_state_change_alarm_short_description))


            }
            notificationManager.notify(1234, builder.build())


        } else {
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
                    .setSmallIcon(R.drawable.ic_gps_disable)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.gps_disable))
                    .setContentText(ctx.getString(R.string.gps_state_change_alarm_short_description))

            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_gps_disable)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(ctx.getString(R.string.gps_disable))
                    .setContentText(ctx.getString(R.string.gps_state_change_alarm_short_description))

            }
            notificationManager.notify(1234, builder.build())

        }
    }
}