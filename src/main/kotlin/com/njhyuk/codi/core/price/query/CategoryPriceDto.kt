package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.CategoryPrice
import com.njhyuk.codi.core.price.domain.PriceType

data class CategoryPriceDto(
    val priceType: PriceType,
    val category: String,
    val brand: String,
    val productNo: String,
    val productPrice: Long
) {
    companion object {
        fun from(categoryPrice: CategoryPrice): CategoryPriceDto {
            return CategoryPriceDto(
                priceType = categoryPrice.priceType,
                category = categoryPrice.category,
                brand = categoryPrice.brand,
                productNo = categoryPrice.productNo,
                productPrice = categoryPrice.productPrice
            )
        }
    }
}
