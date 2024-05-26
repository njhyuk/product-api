package com.njhyuk.codi.inboud.web.internal.v1.products

import io.swagger.v3.oas.annotations.media.Schema

data class CreateProductRequest(
    @Schema(description = "상품 가격", example = "10000")
    val price: Long,
    @Schema(description = "카테고리", example = "상의")
    val category: String,
    @Schema(description = "브랜드", example = "Z")
    val brand: String,
)
