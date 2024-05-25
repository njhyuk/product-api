package com.njhyuk.codi.core.product.event

interface ProductEvent {
    val productNo: Long
    val category: String
    val brand: String
}
