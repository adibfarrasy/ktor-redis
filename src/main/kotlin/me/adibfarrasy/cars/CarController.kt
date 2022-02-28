package me.adibfarrasy.cars

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.addCarRouting() {

    val ID = "{id}"

    // TODO: inject the car service instead of instantiate the class
    route("/car") {

        get("") {

            val getAllCars = CarService().getAllCars()
            call.respond(getAllCars)
        }

        get(ID) {

            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val getSingleCar = CarService().getSingleCar(id)
            call.respond(getSingleCar!!)
        }

        post("") {

            val car = call.receive<Car>()
            val addCar = CarService().createCar(car)
            if (addCar) {

                call.respond("Car successfully created.")
                return@post
            }

            call.respondText(
                "Car cannot be crated.",
                status = HttpStatusCode.UnprocessableEntity
            )
        }

        put(ID) {

            val id = call.parameters["id"] ?: return@put call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val car = call.receive<Car>()
            val editCar = CarService().updateCar(id, car)
            if (editCar) {

                call.respond("Car successfully updated.")
                return@put
            }

            call.respondText(
                "Car cannot be updated.",
                status = HttpStatusCode.UnprocessableEntity
            )
        }

        delete(ID) {

            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val deleteCar = CarService().deleteCar(id)
            if (deleteCar) {

                call.respond("Car successfully deleted.")
                return@delete
            }

            call.respondText(
                "Car cannot be deleted.",
                status = HttpStatusCode.UnprocessableEntity
            )
        }
    }
}
