package com.example.notificationandalarm.view.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notificationandalarm.R
import com.example.notificationandalarm.background_task.BatteryLowReceiver
import kotlinx.android.synthetic.main.fragment_notify_battery_low.view.*


class NotifyBatteryLowFragment : Fragment() {

    private var batteryLowReceiver: BatteryLowReceiver = BatteryLowReceiver()
    private var alarmSetFlag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notify_battery_low, container, false)
        view.btnSetAlarm.setOnClickListener {
            alarmSetFlag = true
            activity?.registerReceiver(
                batteryLowReceiver,
                IntentFilter(Intent.ACTION_BATTERY_CHANGED)
            )
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()

        }

        view.btnStopAlarm.setOnClickListener {
            if (alarmSetFlag) {
                alarmSetFlag = false
                activity?.unregisterReceiver(batteryLowReceiver)
                Toast.makeText(
                    activity,
                    activity?.getString(R.string.stop_alarm),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (alarmSetFlag) {
            activity?.unregisterReceiver(batteryLowReceiver)
        }
    }
}