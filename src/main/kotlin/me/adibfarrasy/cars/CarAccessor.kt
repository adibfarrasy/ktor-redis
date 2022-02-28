package me.adibfarrasy.cars

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import me.adibfarrasy.plugins.configurePersistence
import java.util.*

class CarAccessor {

    // TODO: instantiate the persistence layer as a singleton
    private val jedis = configurePersistence()
    private val gson = Gson()

    suspend fun findAll(): List<Car> {

        val result: List<Car>
        coroutineScope {

            val findAll = async {

                jedis.keys("*").map { gson.fromJson(jedis.get(it), Car::class.java) }
            }

            result = findAll.await()
        }

        return result
    }

    suspend fun findOne(id: String): Car? {

        val result: Car?
        coroutineScope {

            val findOne = async {

                gson.fromJson(jedis.get(id), Car::class.java)
            }

            result = findOne.await()
        }

        return result
    }

    suspend fun add(car: Car): Boolean {

        val uuid = UUID.randomUUID().toString()
        return try {

            coroutineScope {

                withContext(Dispatchers.Default) {
                    car.id = uuid
                    jedis.set(uuid, gson.toJson(car))
                }

            }

            true
        } catch (e: Exception) {

            println("Unknown error: $e")
            false
        }
    }

    suspend fun update(id: String, car: Car): Boolean {

        return try {

            var success: Boolean
            car.id = id
            coroutineScope {

                withContext(Dispatchers.Default) {

                    val carExists = jedis.get(id) != null
                    success = if (carExists) {
                        jedis.set(id, gson.toJson(car))
                        true

                    } else {

                        false
                    }

                }

            }

            success
        } catch (e: Exception) {

            println("Unknown error: $e")
            false
        }

    }

    suspend fun delete(id: String): Boolean {

        return try {

            coroutineScope {
                withContext(Dispatchers.Default) {

                    jedis.del(id)
                }

            }

            true
        } catch (e: Exception) {

            println("Unknown error: $e")
            false
        }
    }
}