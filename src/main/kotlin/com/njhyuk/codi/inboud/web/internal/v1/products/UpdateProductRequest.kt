package com.njhyuk.codi.inboud.web.internal.v1.products

data class UpdateProductRequest(
    val price: Double,
    val category: String,
    val brand: String,
)
