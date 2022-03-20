package com.example.lab6android.model

data class Car(
    val id: Long,
    val photo: String,
    val name: String,
    val carNumber: String,
    val carCapacity: Int
)

data class Schedule(
    val id: Long,
    val car: Car,
    val route: String,
    val departDate: String,
    val departTime: String,
    val arriveDate: String,
    val arriveTime: String
)
