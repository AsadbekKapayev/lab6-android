package com.example.lab6android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6android.databinding.ActivityMainBinding
import com.example.lab6android.model.Schedule
import com.example.lab6android.model.ScheduleService
import com.example.lab6android.model.SchedulesListener

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.routes -> openSecondActivity()
                }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }
        binding.button.setOnClickListener { binding.drawer.openDrawer(GravityCompat.START) }
    }

    private fun openSecondActivity() {
        val intent = Intent(this, ScheduleListActivity::class.java)
        startActivity(intent)
    }
}