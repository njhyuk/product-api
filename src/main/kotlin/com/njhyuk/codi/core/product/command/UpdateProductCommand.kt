package com.njhyuk.codi.core.product.command

data class UpdateProductCommand(
    val productNo: Long,
    val price: Double,
    val category: String,
    val brand: String,
)
