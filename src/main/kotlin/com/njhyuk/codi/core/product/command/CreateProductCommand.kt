package com.njhyuk.codi.core.product.command

data class CreateProductCommand(
    val price: Long,
    val category: String,
    val brand: String
)
