package com.example.notificationandalarm.view.fragment

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notificationandalarm.R
import kotlinx.android.synthetic.main.fragment_notify_device_idle.view.*

class SetPredefinedTimeAlarmFragment : Fragment() {


    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val alarmManager: AlarmManager =
            activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val view = inflater.inflate(R.layout.fragment_set_predefined_time_alarm, container, false)
        view.btnSetAlarm.setOnClickListener {
            val intentAlarm = Intent()
            intentAlarm.action = "com.alarm.notification"
            intentAlarm.addCategory("android.intent.category.DEFAULT")
            val pendingIntent =
                PendingIntent.getBroadcast(
                    activity,
                    0,
                    intentAlarm,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            alarmManager.set(AlarmManager.RTC_WAKEUP, (1000 * 30), pendingIntent)
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()
        }

        view.btnStopAlarm.setOnClickListener {
            val intentAlarm = Intent()
            intentAlarm.action = "com.alarm.notification"
            intentAlarm.addCategory("android.intent.category.DEFAULT")
            val pendingIntent =
                PendingIntent.getBroadcast(
                    activity,
                    0,
                    intentAlarm,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            alarmManager.cancel(pendingIntent)
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()


        }
        return view
    }
}