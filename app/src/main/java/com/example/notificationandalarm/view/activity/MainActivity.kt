package com.example.notificationandalarm.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationandalarm.R
import com.example.notificationandalarm.adapter.RecyclerSetAlarmListAdapter
import com.example.notificationandalarm.model.AlarmListModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val alarmList = ArrayList<AlarmListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createAlarmList()
    }


    private fun createAlarmList() {
        alarmList.add(
            AlarmListModel(
                getString(R.string.charge_mode_alarm),
                getString(R.string.charge_mode_alarm_short_description),
                getString(R.string.charge_mode_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.battery_low_alarm),
                getString(R.string.battery_low_short_description),
                getString(R.string.battery_low_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.network_state_change),
                getString(R.string.network_change_short_description),
                getString(R.string.network_change_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.device_idle_alarm),
                getString(R.string.device_idle_alarm_short_description),
                getString(R.string.device_idle_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.gps_alarm),
                getString(R.string.gps_state_change_alarm_short_description),
                getString(R.string.gps_state_change_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.predefined_time),
                getString(R.string.predefined_time_alarm_short_description),
                getString(R.string.predefined_time_alarm_long_description)
            )
        )
        alarmList.add(
            AlarmListModel(
                getString(R.string.after_certain_amount_time),
                getString(R.string.set_alarm_after_some_time_short_description),
                getString(R.string.set_alarm_after_some_time_long_description)
            )
        )
        setAdapterAlarmList()

    }


    private fun setAdapterAlarmList() {
        val alarmAdapter = RecyclerSetAlarmListAdapter(alarmList, this@MainActivity)
        //rvAlarmList.layoutManager = LinearLayoutManager(this@MainActivity)
        rvAlarmList.adapter = alarmAdapter
        //  val dividerItemDecoration = DividerItemDecoration(rvAlarmList.context,LinearLayoutManager.HORIZONTAL)
        //rvAlarmList.addItemDecoration(dividerItemDecoration)
    }


}