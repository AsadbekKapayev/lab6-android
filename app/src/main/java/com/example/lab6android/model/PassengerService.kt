package com.example.lab6android.model

import com.github.javafaker.Faker

typealias PassengerListener = (passengers: List<Passenger>) -> Unit
typealias OpenSpotListener = (passengers: List<Passenger>) -> Unit

class PassengerService {
    private var passengers = mutableListOf<Passenger>()
    private var freePlaces = mutableListOf<Passenger>()
    private val listeners = mutableListOf<PassengerListener>()

    init {
        val faker = Faker.instance()

        freePlaces = (6..40 step 2).map {
            Passenger(
                id = it.toLong(),
                name = "Нет имени",
                place = (it / 2).toString(),
                part_place = PartPlace.TOP,
                type = PassengerType.NO_TYPE
            )
        }.toMutableList()

        for (i in (0..5) step 2) {
            passengers.add(
                Passenger(
                    id = i.toLong(),
                    name = faker.artist().name(),
                    place = (i / 2).toString(),
                    part_place = PartPlace.TOP,
                    type = PassengerType.ONLINE
                )
            )
            passengers.add(
                Passenger(
                    id = (i + 1).toLong(),
                    name = faker.artist().name(),
                    place = (i / 2).toString(),
                    part_place = PartPlace.BOTTOM,
                    type = PassengerType.OFFLINE
                )
            )
        }

        for (i in (7..40) step 2) {
            freePlaces.add(
                Passenger(
                    id = i.toLong(),
                    name = "Нет имени",
                    place = (i / 2).toString(),
                    part_place = PartPlace.BOTTOM,
                    type = PassengerType.NO_TYPE
                )
            )
        }

        passengers.sortBy { it.id }
        freePlaces.sortBy { it.id }
    }

    fun getPassengers(): List<Passenger> {
        return passengers
    }

    fun deletePassenger(passenger: Passenger) {
        val indexToDelete = passengers.indexOfFirst { it.id == passenger.id }
        if (indexToDelete != -1) {
            passengers.removeAt(indexToDelete)
        }
        notifyChanges()
    }

    fun addListener(listener: PassengerListener) {
        listeners.add(listener)
        listener.invoke(passengers)
    }

    fun remove(listener: PassengerListener) {
        listeners.remove(listener)
    }

    fun addOpenListener(listener: OpenSpotListener) {
        listeners.add(listener)
        listener.invoke(freePlaces)
    }

    fun removeOpen(listener: OpenSpotListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach {
            it.invoke(passengers)
            it.invoke(freePlaces)
        }
    }
}