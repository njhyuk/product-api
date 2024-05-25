package com.njhyuk.codi.config.exception

open class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
