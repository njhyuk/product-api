package com.njhyuk.codi.config.exception

enum class ErrorCode(
    val code: String,
    val message: String
) {
    NOT_EXISTS_PRODUCT("NOT_EXISTS_PRODUCT", "상품이 존재하지 않습니다."),
    REDIS_LOCK_FAIL("REDIS_LOCK_FAIL", "Redis 락 획득 실패")
}
