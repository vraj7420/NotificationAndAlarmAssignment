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
import com.example.notificationandalarm.backgroundtask.DeviceIdleStateReceiver
import kotlinx.android.synthetic.main.fragment_notify_device_idle.view.*

class NotifyDeviceIdleFragment : Fragment() {
    private var deviceIdleState: DeviceIdleStateReceiver = DeviceIdleStateReceiver()
    private var alarmSetFlag = false
    private val intentFilter = IntentFilter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notify_device_idle, container, false)

        view.btnSetAlarm.setOnClickListener {
            alarmSetFlag = true
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
            intentFilter.addAction(Intent.ACTION_SCREEN_ON)
            activity?.registerReceiver(deviceIdleState, intentFilter)
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()
        }

        view.btnStopAlarm.setOnClickListener {
            if (alarmSetFlag) {
                alarmSetFlag = false
                activity?.unregisterReceiver(deviceIdleState)
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
            activity?.unregisterReceiver(deviceIdleState)
        }

    }
}
