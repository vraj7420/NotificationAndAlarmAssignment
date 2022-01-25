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
import kotlinx.android.synthetic.main.fragment_set_alarm_after_some_time.*


class SetAlarmAfterSomeTimeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_set_alarm_after_some_time, container, false)

    }

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val alarmManager: AlarmManager =
            activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        view.btnSetAlarm.setOnClickListener {
            val intentAlarm = Intent()
            intentAlarm.action = "com.alarm.notification.RepeatingAlarm"
            intentAlarm.addCategory("android.intent.category.DEFAULT")
            val pendingIntent = PendingIntent.getBroadcast(
                activity,
                0,
                intentAlarm,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            if (tetRepeatingAlarmTime.text!!.isEmpty()) {
                tetRepeatingAlarmTime.error = "For After Some Time  Alarm  set First Enter Time"
                tetRepeatingAlarmTime.requestFocus()
            } else {
                val repeatingTime = tetRepeatingAlarmTime.text.toString().toLong()
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + (1000 * 1 * repeatingTime),
                    pendingIntent
                )
                Toast.makeText(
                    activity,
                    activity?.getString(R.string.set_alarm),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        view.btnStopAlarm.setOnClickListener {
            val intentAlarm = Intent()
            intentAlarm.action = "com.alarm.notification.RepeatingAlarm"
            intentAlarm.addCategory("android.intent.category.DEFAULT")
            val pendingIntent = PendingIntent.getBroadcast(
                activity,
                0,
                intentAlarm,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            alarmManager.cancel(pendingIntent)
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()
        }

    }

}