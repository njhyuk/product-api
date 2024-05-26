package com.njhyuk.codi.extension.lock

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component

private const val REDISSON_LOCK_PREFIX = "CODI_LOCK:"

@Aspect
@Component
class RedisDistributedLockAspect(
    private val redissonClient: RedissonClient,
) {
    @Around("@annotation (com.njhyuk.codi.extension.lock.RedisDistributedLock)")
    fun proceed(joinPoint: ProceedingJoinPoint): Any? {
        val methodSignature = joinPoint.signature as MethodSignature
        val annotation = methodSignature.method.getAnnotation(RedisDistributedLock::class.java)
        val lockKey = "$REDISSON_LOCK_PREFIX${annotation.key}"
        val redisLock = redissonClient.getLock(lockKey)

        try {
            if (!redisLock.tryLock(annotation.waitTime, annotation.leaseTime, annotation.timeUnit)) {
                throw RedisDistributedLockException()
            }
            return joinPoint.proceed()
        } finally {
            if (redisLock.isLocked && redisLock.isHeldByCurrentThread)
                redisLock.unlock()
        }
    }
}
