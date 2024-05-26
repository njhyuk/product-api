package com.njhyuk.codi.inboud.web.api.v1.brands

import com.njhyuk.codi.core.price.query.BrandCategoryPriceDto
import com.njhyuk.codi.core.price.query.GetBrandCategoryPricesDto
import io.swagger.v3.oas.annotations.media.Schema
import com.njhyuk.codi.inboud.web.api.v1.brands.BrandPriceItemResponse as BrandPriceItem
import com.njhyuk.codi.inboud.web.api.v1.brands.BrandLowestPriceItemResponse as PriceItem

data class GetBrandLowestPricesResponse(
    @Schema(description = "최저가")
    val lowestPrice: PriceItem
) {
    companion object {
        fun of(
            brand: String,
            brandCategoryPrices: GetBrandCategoryPricesDto
        ): GetBrandLowestPricesResponse {
            return GetBrandLowestPricesResponse(
                PriceItem(
                    brand = brand,
                    categories = brandCategoryPrices.prices.map { BrandPriceItem.from(it) },
                    totalPrice = brandCategoryPrices.totalPrice
                )
            )
        }
    }
}

data class BrandLowestPriceItemResponse(
    @Schema(description = "브랜드", example = "A")
    val brand: String,
    @Schema(description = "카테고리")
    val categories: List<BrandPriceItem>,
    @Schema(description = "총액", example = "80000")
    val totalPrice: Long
)

data class BrandPriceItemResponse(
    @Schema(description = "카테고리", example = "상의")
    val category: String,
    @Schema(description = "가격", example = "10000")
    val productPrice: Long
) {
    companion object {
        fun from(price: BrandCategoryPriceDto): BrandPriceItem {
            return BrandPriceItem(
                category = price.category,
                productPrice = price.productPrice
            )
        }
    }
}
