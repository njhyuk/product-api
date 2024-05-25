package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandCategoryPrice

data class BrandCategoryPriceDto(
    val category: String,
    val brand: String,
    val productNo: String,
    val productPrice: Long
) {
    companion object {
        fun from(categoryPrice: BrandCategoryPrice): BrandCategoryPriceDto {
            return BrandCategoryPriceDto(
                category = categoryPrice.category,
                brand = categoryPrice.brand,
                productNo = categoryPrice.productNo,
                productPrice = categoryPrice.productPrice
            )
        }
    }
}
