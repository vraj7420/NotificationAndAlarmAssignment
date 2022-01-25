package com.example.notificationandalarm.view.fragment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notificationandalarm.R
import com.example.notificationandalarm.background_task.NetworkStateReceiver
import kotlinx.android.synthetic.main.fragment_notify_network_change.view.*


class NotifyNetworkChangeFragment : Fragment() {
    private var networkStateChangeReceiver: NetworkStateReceiver = NetworkStateReceiver()
    private var alarmSetFlag = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_network_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnSetAlarm.setOnClickListener {
            activity?.registerReceiver(
                networkStateChangeReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
        view.btnStopAlarm.setOnClickListener {
            if (alarmSetFlag) {
                alarmSetFlag = false
                activity?.unregisterReceiver(networkStateChangeReceiver)
                Toast.makeText(
                    activity,
                    activity?.getString(R.string.set_alarm),
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (alarmSetFlag) {
            activity?.unregisterReceiver(networkStateChangeReceiver)
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()

        }
    }
}