package com.example.notificationandalarm.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notificationandalarm.R
import com.example.notificationandalarm.background_task.GpsEnableDisableService
import kotlinx.android.synthetic.main.fragment_notify_gps_state_change.view.*

class NotifyGpsStateChangeFragment : Fragment() {
    private lateinit var intentService: Intent
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notify_gps_state_change, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnSetAlarm.setOnClickListener {
            intentService = Intent(activity, GpsEnableDisableService::class.java)
            activity?.startService(intentService)
            Toast.makeText(activity, activity?.getString(R.string.set_alarm), Toast.LENGTH_SHORT)
                .show()
        }

        view.btnStopAlarm.setOnClickListener {
            activity?.stopService(intentService)
            Toast.makeText(activity, activity?.getString(R.string.stop_alarm), Toast.LENGTH_SHORT)
                .show()

        }

    }


}
