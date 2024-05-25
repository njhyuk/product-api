package com.njhyuk.codi.core.price.query

data class GetCategoryPricesDto(
    val prices: List<CategoryPriceDto>,
    val totalPrice: Long
)
