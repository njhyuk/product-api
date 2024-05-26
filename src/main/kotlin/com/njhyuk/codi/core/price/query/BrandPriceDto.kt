package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandLowestPrice

data class BrandPriceDto(
    val brand: String,
    val totalPrice: Long
) {
    companion object {
        fun from(brandPrice: BrandLowestPrice): BrandPriceDto {
            return BrandPriceDto(
                brand = brandPrice.brand,
                totalPrice = brandPrice.totalPrice
            )
        }
    }
}
