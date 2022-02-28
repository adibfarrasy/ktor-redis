package me.adibfarrasy.plugins

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.Protocol

fun configurePersistence(): Jedis {

    // TODO: extract parameters to configuration
    val HOSTNAME = ""
    val USER = ""
    val PASSWORD = ""

    val jedisPool = JedisPool(
        JedisPoolConfig(),
        HOSTNAME,
        19703,
        Protocol.DEFAULT_TIMEOUT,
        USER,
        PASSWORD
    )

    return jedisPool.resource
}