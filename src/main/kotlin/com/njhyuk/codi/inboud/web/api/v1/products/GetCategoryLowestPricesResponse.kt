package com.njhyuk.codi.inboud.web.api.v1.products

import com.njhyuk.codi.core.price.query.CategoryPriceDto
import com.njhyuk.codi.core.price.query.GetCategoryPricesDto

data class GetCategoryLowestPricesResponse(
    val prices: List<CategoryPriceItemResponse>,
    val totalPrice: Long
) {
    companion object {
        fun from(response: GetCategoryPricesDto): GetCategoryLowestPricesResponse {
            return GetCategoryLowestPricesResponse(
                prices = response.prices.map { CategoryPriceItemResponse.from(it) },
                totalPrice = response.totalPrice
            )
        }
    }
}

data class CategoryPriceItemResponse(
    val category: String,
    val brand: String,
    val productPrice: Long
) {
    companion object {
        fun from(price: CategoryPriceDto): CategoryPriceItemResponse {
            return CategoryPriceItemResponse(
                category = price.category,
                brand = price.brand,
                productPrice = price.productPrice
            )
        }
    }
}
