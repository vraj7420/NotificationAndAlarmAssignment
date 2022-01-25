package com.example.notificationandalarm.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notificationandalarm.R
import com.example.notificationandalarm.constant.ConstantString
import kotlinx.android.synthetic.main.activity_notification_description.*
import kotlinx.android.synthetic.main.activity_set_alarm_screen.tvAlarmTitle

class NotificationDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_description)
        tvAlarmTitle.text = intent.getStringExtra(ConstantString.alarmTitle)
        tvSetAlarmShortDescription.text =
            intent.getStringExtra(ConstantString.alarmShortDescription)
        tvSetAlarmLongDescription.text = intent.getStringExtra(ConstantString.alarmLongDescription)

    }

}