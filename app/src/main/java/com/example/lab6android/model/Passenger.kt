package com.example.lab6android.model

data class Passenger(
    val id: Long,
    val name: String,
    val place: String,
    val part_place: PartPlace,
    val type: PassengerType
)

enum class PartPlace {
    TOP, BOTTOM
}

enum class PassengerType {
    OFFLINE, ONLINE, NO_TYPE
}