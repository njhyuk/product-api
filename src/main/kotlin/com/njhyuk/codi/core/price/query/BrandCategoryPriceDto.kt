package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPrice

data class BrandCategoryPriceDto(
    val category: String,
    val brand: String,
    val productNo: Long,
    val productPrice: Long
) {
    companion object {
        fun from(categoryPrice: BrandCategoryLowestPrice): BrandCategoryPriceDto {
            return BrandCategoryPriceDto(
                category = categoryPrice.category,
                brand = categoryPrice.brand,
                productNo = categoryPrice.productNo,
                productPrice = categoryPrice.productPrice
            )
        }
    }
}
