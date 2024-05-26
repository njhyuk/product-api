package com.njhyuk.codi.inboud.web

import com.njhyuk.codi.config.exception.ErrorCode
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "표준 응답")
data class WebResponse<T>(
    @Schema(description = "성공 여부")
    val success: Boolean,
    @Schema(description = "에러 코드")
    val code: String? = null,
    @Schema(description = "에러 메시지")
    val message: String? = null,
    @Schema(description = "응답 데이터")
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
