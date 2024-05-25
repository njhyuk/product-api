package com.njhyuk.codi.core.product.event

data class ProductDeletedEvent(
    override val productNo: Long,
    override val category: String,
    override val brand: String,
) : ProductEvent

