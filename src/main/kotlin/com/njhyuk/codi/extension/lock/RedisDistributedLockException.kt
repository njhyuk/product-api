package com.njhyuk.codi.extension.lock

import com.njhyuk.codi.config.exception.BusinessException
import com.njhyuk.codi.config.exception.ErrorCode

class RedisDistributedLockException : BusinessException(ErrorCode.REDIS_LOCK_FAIL)
