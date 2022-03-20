package com.example.lab6android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab6android.databinding.ItemScheduleBinding
import com.example.lab6android.model.Schedule

interface ScheduleActionListener {
    fun onScheduleDelete(schedule: Schedule)
}

class SchedulesAdapter(
    private val actionListener: ScheduleActionListener
) : RecyclerView.Adapter<SchedulesAdapter.ScheduleViewHolder>(), View.OnClickListener {

    var schedules: List<Schedule> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        val schedule = v.tag as Schedule
        when (v.id) {
            R.id.deleteRouteButton ->
                actionListener.onScheduleDelete(schedule)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemScheduleBinding.inflate(inflater, parent, false)

        binding.deleteRouteButton.setOnClickListener(this)

        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schedule = schedules[position]

        with(holder.binding) {
            holder.itemView.tag = schedule
            deleteRouteButton.tag = schedule
            autoNameTextView.text = schedule.car.name
            autoNumTextView.text = schedule.car.carNumber
            autoCapacityTextView.setText(R.string.places)
            autoCapacityTextView.text = schedule.car.carCapacity.toString() + " " +  autoCapacityTextView.text
            autoRouteTextView.text = schedule.route
            departDateTextView.text = schedule.departDate
            departTimeTextView.text = schedule.departTime
            arriveDateTextView.text = schedule.arriveDate
            arriveTimeTextView.text = schedule.arriveTime
            if (schedule.car.photo.isNotBlank()) {
                Glide.with(autoImageView.context)
                    .load(schedule.car.photo)
                    .placeholder(R.drawable.ic_car_photo)
                    .error(R.drawable.ic_car_photo)
                    .into(autoImageView)
            } else {
                Glide.with(autoImageView.context).clear(autoImageView)
                autoImageView.setImageResource(R.drawable.ic_car_photo)
            }
        }
    }

    override fun getItemCount(): Int {
        return schedules.size
    }

    class ScheduleViewHolder(
        val binding: ItemScheduleBinding
    ) : RecyclerView.ViewHolder(binding.root)
}