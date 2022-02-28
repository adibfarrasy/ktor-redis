package me.adibfarrasy.cars

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    var id: String,
    private val name: String,
    private val description: String,
    private val imageUrl: String,
)
