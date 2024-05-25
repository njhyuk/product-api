package com.njhyuk.codi.core.product.command

data class CreateProductCommand(
    val price: Double,
    val category: String,
    val brand: String,
)
