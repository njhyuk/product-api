package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.query.CategoryPriceDto
import com.njhyuk.codi.core.price.query.GetCategoryPricesDto
import com.njhyuk.codi.inboud.web.api.v1.categories.CategoryLowestPriceItemResponse as PriceItem


data class GetCategoryLowestPricesResponse(
    val prices: List<PriceItem>,
    val totalPrice: Long
) {
    companion object {
        fun from(response: GetCategoryPricesDto): GetCategoryLowestPricesResponse {
            return GetCategoryLowestPricesResponse(
                prices = response.prices.map { PriceItem.from(it) },
                totalPrice = response.totalPrice
            )
        }
    }
}

data class CategoryLowestPriceItemResponse(
    val category: String,
    val brand: String,
    val productPrice: Long
) {
    companion object {
        fun from(price: CategoryPriceDto): PriceItem {
            return PriceItem(
                category = price.category,
                brand = price.brand,
                productPrice = price.productPrice
            )
        }
    }
}
