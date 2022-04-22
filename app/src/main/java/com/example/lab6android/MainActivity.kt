package com.example.lab6android

import com.example.lab6android.model.PassengerService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6android.databinding.ActivityMainBinding
import com.example.lab6android.model.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var passengerService = PassengerService();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.routes -> openSecondActivity()
                R.id.passengers -> openPassengersActivity()
            }
            binding.drawer.closeDrawer(GravityCompat.START)
            true
        }
        binding.button.setOnClickListener { binding.drawer.openDrawer(GravityCompat.START) }
    }

    private fun openPassengersActivity() {
        val intent = Intent(this, PassengersActivity::class.java)
        startActivity(intent)
    }

    private fun openSecondActivity() {
        val intent = Intent(this, ScheduleListActivity::class.java)
        startActivity(intent)
    }
}