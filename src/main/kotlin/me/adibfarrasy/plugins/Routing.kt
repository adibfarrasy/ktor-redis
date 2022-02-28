package me.adibfarrasy.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import me.adibfarrasy.cars.addCarRouting

fun Application.configureRouting() {

    routing {
        addCarRouting()
    }
}
