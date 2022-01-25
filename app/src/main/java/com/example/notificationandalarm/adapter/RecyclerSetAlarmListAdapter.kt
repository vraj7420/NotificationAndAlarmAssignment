package com.example.notificationandalarm.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notificationandalarm.R
import com.example.notificationandalarm.model.AlarmListModel
import com.example.notificationandalarm.view.activity.SetAlarmScreenActivity

class RecyclerSetAlarmListAdapter(
    private var setAlarmListModel: ArrayList<AlarmListModel>,
    private var ctx: Context
) :
    RecyclerView.Adapter<RecyclerSetAlarmListAdapter.SetNotificationAlarmViewHolder>() {

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SetNotificationAlarmViewHolder {
        val recyclerInflater = LayoutInflater.from(ctx)
        val recyclerView =
            recyclerInflater.inflate(R.layout.item_of_alarm_list_layout, parent, false)
        return SetNotificationAlarmViewHolder(recyclerView)

    }


    override fun onBindViewHolder(holder: SetNotificationAlarmViewHolder, position: Int) {
        val alarmList = setAlarmListModel[position]
        holder.alarmTitle.text = alarmList.alarmTitle
        holder.alarmShortDescription.text = alarmList.alarmShortDescription
        holder.alarmLongDescription.text = alarmList.alarmLongDescription

    }


    override fun getItemCount(): Int {
        return setAlarmListModel.size
    }


    inner class SetNotificationAlarmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var alarmTitle: TextView
        var alarmShortDescription: TextView
        var alarmLongDescription: TextView


        @SuppressLint("UnspecifiedImmutableFlag")
        override fun onClick(v: View?) {
            val position = adapterPosition
            val alarm = setAlarmListModel[position]
            val intent = Intent(ctx, SetAlarmScreenActivity::class.java)
            intent.putExtra("alarm Title", alarm.alarmTitle)
            intent.putExtra("alarm Description", alarm.alarmShortDescription)
            ctx.startActivity(intent)
        }


        init {
            itemView.setOnClickListener(this)
            alarmTitle = itemView.findViewById(R.id.tvSetAlarm)
            alarmShortDescription = itemView.findViewById(R.id.tvSetAlarmShortDescription)
            alarmLongDescription = itemView.findViewById(R.id.tvAlarmLongDescription)

        }
    }
}