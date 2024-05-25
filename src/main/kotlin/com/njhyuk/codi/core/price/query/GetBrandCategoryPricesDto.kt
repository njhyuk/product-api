package com.njhyuk.codi.core.price.query

data class GetBrandCategoryPricesDto(
    val prices: List<BrandCategoryPriceDto>,
    val totalPrice: Long
)
