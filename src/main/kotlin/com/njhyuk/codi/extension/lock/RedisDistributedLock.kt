package com.njhyuk.codi.extension.lock

import java.util.concurrent.TimeUnit

annotation class RedisDistributedLock(
    val key: String,
    val waitTime: Long = 0,
    val leaseTime: Long = 0,
    val timeUnit: TimeUnit = TimeUnit.SECONDS
)
