package com.njhyuk.codi.inboud.web.internal.v1.products

import io.swagger.v3.oas.annotations.media.Schema

data class CreateProductResponse(
    @Schema(description = "상품 번호", example = "123")
    val productNo: Long
)
