package com.example.notificationandalarm.view.fragment

import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notificationandalarm.R
import com.example.notificationandalarm.backgroundtask.GpsEnableDisableReceiver
import kotlinx.android.synthetic.main.fragment_notify_gps_state_change.view.*

class NotifyGpsStateChangeFragment : Fragment() {
    private var gpsStateChangeReceiver: GpsEnableDisableReceiver = GpsEnableDisableReceiver()
    private var alarmSetFlag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notify_gps_state_change, container, false)
        view.btnSetAlarm.setOnClickListener {
            alarmSetFlag = true
            activity?.registerReceiver(
                gpsStateChangeReceiver,
                IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
            )
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()
        }

        view.btnStopAlarm.setOnClickListener {
            if (alarmSetFlag) {
                alarmSetFlag = false
                activity?.unregisterReceiver(gpsStateChangeReceiver)
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
            activity?.unregisterReceiver(gpsStateChangeReceiver)
        }
    }

}
