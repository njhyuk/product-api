package com.njhyuk.codi.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.springframework.context.annotation.Configuration
import redis.embedded.RedisServer
import org.redisson.config.Config
import org.springframework.context.annotation.Bean


@Configuration
class RedisEmbeddedConfig {
    private lateinit var redisServer: RedisServer
    private val redisPort: Int = 6379

    @PostConstruct
    fun startRedis() {
        redisServer = RedisServer(redisPort)
        redisServer.start()
    }

    @PreDestroy
    fun stopRedis() {
        redisServer.stop()
    }

    @Bean
    fun redissonClient(): RedissonClient? {
        val config = Config()
        config.useSingleServer().address = "redis://localhost:${redisPort}"
        return Redisson.create(config)
    }
}
