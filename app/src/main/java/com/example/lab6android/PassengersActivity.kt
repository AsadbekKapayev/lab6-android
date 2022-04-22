package com.example.lab6android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab6android.databinding.ActivityPassengersBinding
import com.example.lab6android.model.OpenSpotListener
import com.example.lab6android.model.PassengerListener
import com.example.lab6android.model.PassengerService

class PassengersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPassengersBinding
    private lateinit var adapter: PassengersAdapter
    private lateinit var openSpotAdapter: PassengersAdapter
    private lateinit var passengerService: PassengerService
    private lateinit var openSpotPassengerService: PassengerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassengersBinding.inflate(layoutInflater).also { setContentView(it.root) }
        passengerService = PassengerService()
        openSpotPassengerService = PassengerService()
        adapter = PassengersAdapter()
        openSpotAdapter = PassengersAdapter()

        openSpotPassengerService.addOpenListener(openSpotPassengerListListener)
        passengerService.addListener(passengerListener)

        val layoutManager = LinearLayoutManager(this)
        val layoutManager2 = LinearLayoutManager(this)
        binding.passengersRecyclerView.layoutManager = layoutManager
        binding.passengersRecyclerView.adapter = adapter

        binding.openSpotRecyclerView.layoutManager = layoutManager2
        binding.openSpotRecyclerView.adapter = openSpotAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        passengerService.remove(passengerListener)
        openSpotPassengerService.removeOpen(openSpotPassengerListListener)
    }

    private val passengerListener: PassengerListener = {
        adapter.passengers = it
    }

    private val openSpotPassengerListListener: OpenSpotListener = {
        openSpotAdapter.passengers = it
    }
}