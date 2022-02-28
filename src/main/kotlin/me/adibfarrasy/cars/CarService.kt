package me.adibfarrasy.cars

class CarService {

    // TODO: inject the car accessor instead of instantiate the class
    suspend fun getAllCars(): List<Car> {
        return CarAccessor().findAll()
    }

    suspend fun getSingleCar(id: String): Car? {
        return CarAccessor().findOne(id)
    }

    suspend fun createCar(car: Car): Boolean {
        return CarAccessor().add(car)
    }

    suspend fun updateCar(id: String, car: Car): Boolean {
        return CarAccessor().update(id, car)
    }

    suspend fun deleteCar(id: String): Boolean {
        return CarAccessor().delete(id)
    }
}