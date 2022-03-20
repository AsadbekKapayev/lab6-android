package com.example.lab6android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6android.databinding.ActivityMainBinding
import com.example.lab6android.databinding.ActivityScheduleListBinding
import com.example.lab6android.model.Schedule
import com.example.lab6android.model.ScheduleService
import com.example.lab6android.model.SchedulesListener

class ScheduleListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleListBinding
    private lateinit var adapter: SchedulesAdapter
    private lateinit var scheduleService: ScheduleService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleListBinding.inflate(layoutInflater).also { setContentView(it.root) }
        scheduleService = ScheduleService()
        adapter = SchedulesAdapter(object : ScheduleActionListener {
            override fun onScheduleDelete(schedule: Schedule) {
                scheduleService.deleteSchedule(schedule)
                Toast.makeText(this@ScheduleListActivity, "Route deleted", Toast.LENGTH_SHORT).show()
            }
        })

        scheduleService.addListener(schedulesListener)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        scheduleService.remove(schedulesListener)
    }

    private val schedulesListener: SchedulesListener = {
        adapter.schedules = it
    }
}