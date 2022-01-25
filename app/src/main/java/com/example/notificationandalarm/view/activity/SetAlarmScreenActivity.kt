package com.example.notificationandalarm.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationandalarm.R
import com.example.notificationandalarm.view.fragment.*
import kotlinx.android.synthetic.main.activity_set_alarm_screen.*

class SetAlarmScreenActivity : AppCompatActivity() {
    private lateinit var alarmTitle: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm_screen)
        alarmTitle = intent.getStringExtra("alarm Title").toString()
        tvAlarmTitle.text = alarmTitle
        setFragment()
    }

    private fun setFragment() {
        val setFragmentManager = supportFragmentManager
        val fragmentTransaction = setFragmentManager.beginTransaction()
        when (alarmTitle) {
            getString(R.string.charge_mode_alarm) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                NotifyChargeModeFragment()
            )
            getString(R.string.battery_low_alarm) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                NotifyBatteryLowFragment()
            )
            getString(R.string.network_state_change) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                NotifyNetworkChangeFragment()
            )
            getString(R.string.gps_alarm) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                NotifyGpsStateChangeFragment()
            )
            getString(R.string.device_idle_alarm) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                NotifyDeviceIdleFragment()
            )
            getString(R.string.predefined_time) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                SetPredefinedTimeAlarmFragment()
            )
            getString(R.string.after_certain_amount_time) -> fragmentTransaction.replace(
                R.id.fragmentAlarm,
                SetAlarmAfterSomeTimeFragment()
            )
        }
        fragmentTransaction.commit()
    }
}