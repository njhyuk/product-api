package com.njhyuk.codi.inboud.web.internal.v1.products

data class CreateProductRequest(
    val price: Long,
    val category: String,
    val brand: String,
)
