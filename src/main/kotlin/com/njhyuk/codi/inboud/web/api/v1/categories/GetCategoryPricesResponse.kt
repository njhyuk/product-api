package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.query.CategoryPriceDto
import com.njhyuk.codi.inboud.web.api.v1.categories.CategoryPricesItemResponse as PriceItem

data class GetCategoryPricesResponse(
    val lowestPrice: PriceItem?,
    val highestPrice: PriceItem?,
) {
    companion object {
        fun from(
            lowestDto: CategoryPriceDto?,
            highestDto: CategoryPriceDto?,
        ): GetCategoryPricesResponse {
            return GetCategoryPricesResponse(
                lowestPrice = lowestDto?.let { PriceItem.from(it) },
                highestPrice = highestDto?.let { PriceItem.from(it) },
            )
        }
    }
}

data class CategoryPricesItemResponse(
    val brand: String,
    val productPrice: Long
) {
    companion object {
        fun from(price: CategoryPriceDto): PriceItem {
            return PriceItem(
                brand = price.brand,
                productPrice = price.productPrice
            )
        }
    }
}
