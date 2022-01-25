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
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notificationandalarm.R
import com.example.notificationandalarm.constant.ConstantString
import com.example.notificationandalarm.view.activity.NotificationDescriptionActivity


class NetworkStateReceiver : BroadcastReceiver() {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private lateinit var pendingIntent: PendingIntent


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnspecifiedImmutableFlag", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(ctx: Context?, intent: Intent?) {
        val notificationIntent = Intent(ctx, NotificationDescriptionActivity::class.java)
        notificationIntent.putExtra(
            ConstantString.alarmTitle,
            ctx!!.getString(R.string.network_state_change)
        )
        notificationIntent.putExtra(
            ConstantString.alarmShortDescription,
            ctx.getString(R.string.network_change_short_description)
        )
        notificationIntent.putExtra(
            ConstantString.alarmLongDescription,
            ctx.getString(R.string.network_change_long_description)
        )

        val connectivityManagerNetworkState =
            ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        pendingIntent =
            PendingIntent.getActivity(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT < 23) {
            val activeNetwork: NetworkInfo? = connectivityManagerNetworkState.activeNetworkInfo

            if (activeNetwork != null) {
                when {
                    (activeNetwork.type == ConnectivityManager.TYPE_WIFI) -> {
                        notificationCreate(
                            ctx,
                            R.drawable.ic_wifi,
                            ctx.getString(R.string.network_state_wifi),
                            ctx.getString(R.string.network_change_short_description)
                        )
                    }
                    (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) -> {
                        notificationCreate(
                            ctx,
                            R.drawable.ic_mobiledata,
                            ctx.getString(R.string.network_state_mobile),
                            ctx.getString(R.string.network_change_short_description)
                        )
                    }
                    (activeNetwork.type == ConnectivityManager.TYPE_BLUETOOTH) -> {
                        notificationCreate(
                            ctx,
                            R.drawable.ic_bluetooth,
                            ctx.getString(R.string.network_state_bluetooth),
                            ctx.getString(R.string.network_change_short_description)
                        )
                    }
                }

            } else {
                notificationCreate(
                    ctx,
                    R.drawable.ic_network_not_connected,
                    ctx.getString(R.string.network_state_not_connected),
                    ctx.getString(R.string.network_change_short_description)
                )

            }

        } else {
            val network = connectivityManagerNetworkState.activeNetwork
            val activeNetwork = connectivityManagerNetworkState.getNetworkCapabilities(network)
            if (activeNetwork != null) {
                when {
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        notificationCreate(
                            ctx,
                            R.drawable.ic_wifi,
                            ctx.getString(R.string.network_state_wifi),
                            ctx.getString(R.string.network_change_short_description)
                        )
                    }
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        notificationCreate(
                            ctx,
                            R.drawable.ic_mobiledata,
                            ctx.getString(R.string.network_state_mobile),
                            ctx.getString(R.string.network_change_short_description)
                        )
                    }
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> {
                        notificationCreate(
                            ctx,
                            R.drawable.ic_bluetooth,
                            ctx.getString(R.string.network_state_bluetooth),
                            ctx.getString(R.string.network_change_short_description)
                        )
                    }
                }

            } else {
                notificationCreate(
                    ctx,
                    R.drawable.ic_network_not_connected,
                    ctx.getString(R.string.network_state_not_connected),
                    ctx.getString(R.string.network_change_short_description)
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