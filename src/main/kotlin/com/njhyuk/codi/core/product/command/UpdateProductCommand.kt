package com.njhyuk.codi.core.product.command

data class UpdateProductCommand(
    val productNo: Long,
    val price: Long,
    val category: String,
    val brand: String
)
