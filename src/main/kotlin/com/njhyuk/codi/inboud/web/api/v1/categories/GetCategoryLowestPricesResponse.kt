package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.query.CategoryPriceDto
import com.njhyuk.codi.core.price.query.GetCategoryPricesDto
import io.swagger.v3.oas.annotations.media.Schema
import com.njhyuk.codi.inboud.web.api.v1.categories.CategoryLowestPriceItemResponse as PriceItem


data class GetCategoryLowestPricesResponse(
    @Schema(description = "카테고리별 최저가 리스트")
    val prices: List<PriceItem>,
    @Schema(description = "총 가격", example = "10000")
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
    @Schema(description = "카테고리", example = "상의")
    val category: String,
    @Schema(description = "브랜드", example = "A")
    val brand: String,
    @Schema(description = "상품 가격", example = "80000")
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
