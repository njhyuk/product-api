package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.query.CategoryPriceDto
import io.swagger.v3.oas.annotations.media.Schema
import com.njhyuk.codi.inboud.web.api.v1.categories.CategoryPricesItemResponse as PriceItem

data class GetCategoryPricesResponse(
    @Schema(description = "최저가")
    val lowestPrice: PriceItem?,
    @Schema(description = "최고가")
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
    @Schema(description = "브랜드", example = "A")
    val brand: String,
    @Schema(description = "상품 가격", example = "10000")
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
