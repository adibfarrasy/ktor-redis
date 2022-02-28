package me.adibfarrasy.plugins

import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.Protocol

fun configurePersistence(): Jedis {

    // TODO: extract parameters to configuration
    val HOSTNAME = "redis-19703.c295.ap-southeast-1-1.ec2.cloud.redislabs.com"
    val USER = "default"
    val PASSWORD = "VghI0ofg0QEEbWHjDl4CfBOReLlVRr31"

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