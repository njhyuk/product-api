package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.query.CategoryPriceDto

data class GetCategoryAllPricesResponse(
    val lowestPrice: CategoryAllPriceItemResponse?,
    val highestPrice: CategoryAllPriceItemResponse?,
) {
    companion object {
        fun from(
            lowestDto: CategoryPriceDto?,
            highestDto: CategoryPriceDto?,
        ): GetCategoryAllPricesResponse {
            return GetCategoryAllPricesResponse(
                lowestPrice = lowestDto?.let { CategoryAllPriceItemResponse.from(it) },
                highestPrice = highestDto?.let { CategoryAllPriceItemResponse.from(it) },
            )
        }
    }
}

data class CategoryAllPriceItemResponse(
    val brand: String,
    val productPrice: Long
) {
    companion object {
        fun from(price: CategoryPriceDto): CategoryAllPriceItemResponse {
            return CategoryAllPriceItemResponse(
                brand = price.brand,
                productPrice = price.productPrice
            )
        }
    }
}
