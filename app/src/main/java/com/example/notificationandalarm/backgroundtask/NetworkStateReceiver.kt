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
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity


class NetworkStateReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private val channelId = "Network State Change"
    private val description = "Set notification"

    @SuppressLint("UnspecifiedImmutableFlag", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra("Alarm Title", ctx!!.getString(R.string.network_state_change))
        notificationIntent.putExtra(
            "Alarm Short Description",
            ctx.getString(R.string.network_change_short_description)
        )
        notificationIntent.putExtra(
            "Alarm Long Description",
            ctx.getString(R.string.network_change_long_description)
        )

        val connectivityManagerNetworkState =
            ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val pendingIntent =
            PendingIntent.getActivity(
                ctx,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

        val network = connectivityManagerNetworkState.activeNetwork
        val activeNetwork = connectivityManagerNetworkState.getNetworkCapabilities(network)
        if (activeNetwork != null) {
            when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {

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
                            .setSmallIcon(R.drawable.ic_wifi)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(ctx.getString(R.string.network_state_wifi))
                            .setContentText(ctx.getString(R.string.network_change_short_description))

                    } else {

                        builder = Notification.Builder(ctx)
                            .setSmallIcon(R.drawable.ic_wifi)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(ctx.getString(R.string.network_state_wifi))
                            .setContentText(ctx.getString(R.string.network_change_short_description))


                    }
                    notificationManager.notify(1234, builder.build())


                }
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
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
                            .setSmallIcon(R.drawable.ic_mobiledata)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(ctx.getString(R.string.network_state_mobile))
                            .setContentText(ctx.getString(R.string.network_change_short_description))

                    } else {

                        builder = Notification.Builder(ctx)
                            .setSmallIcon(R.drawable.ic_mobiledata)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(ctx.getString(R.string.network_state_mobile))
                            .setContentText(ctx.getString(R.string.network_change_short_description))


                    }
                    notificationManager.notify(1234, builder.build())


                }
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
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
                            .setSmallIcon(R.drawable.ic_bluetooth)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(ctx.getString(R.string.network_state_bluetooth))
                            .setContentText(ctx.getString(R.string.network_change_short_description))

                    } else {

                        builder = Notification.Builder(ctx)
                            .setSmallIcon(R.drawable.ic_bluetooth)
                            .setContentIntent(pendingIntent)
                            .setContentTitle(ctx.getString(R.string.network_state_bluetooth))
                            .setContentText(ctx.getString(R.string.network_change_short_description))


                    }
                    notificationManager.notify(1234, builder.build())
                }
            }

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
                    .setSmallIcon(R.drawable.ic_network_not_connected)
                    .setContentIntent(pendingIntent)
                    .setContentText(ctx.getString(R.string.network_state_not_connected))
                    .setContentText(ctx.getString(R.string.network_change_short_description))

            } else {

                builder = Notification.Builder(ctx)
                    .setSmallIcon(R.drawable.ic_network_not_connected)
                    .setContentIntent(pendingIntent)
                    .setContentText(ctx.getString(R.string.network_state_not_connected))
                    .setContentText(ctx.getString(R.string.network_change_short_description))


            }
            notificationManager.notify(1234, builder.build())


        }
    }
}