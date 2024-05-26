package com.njhyuk.codi.inboud.web

import com.njhyuk.codi.config.exception.ErrorCode

data class WebResponse<T>(
    val success: Boolean,
    val code: String? = null,
    val message: String? = null,
    val data: T?
) {
    companion object {
        fun <T> success(result: T): WebResponse<T> {
            return WebResponse(
                success = true,
                data = result
            )
        }

        fun <T> error(errorCode: ErrorCode): WebResponse<T> {
            return WebResponse(
                success = false,
                code = errorCode.code,
                message = errorCode.message,
                data = null
            )
        }
    }
}
