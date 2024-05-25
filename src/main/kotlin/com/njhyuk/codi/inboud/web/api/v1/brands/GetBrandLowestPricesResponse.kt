package com.njhyuk.codi.inboud.web.api.v1.brands

import com.njhyuk.codi.core.price.query.BrandCategoryPriceDto
import com.njhyuk.codi.core.price.query.BrandPriceDto
import com.njhyuk.codi.core.price.query.GetBrandCategoryPricesDto

data class GetBrandLowestPricesResponse(
    val lowestPrice: LowestPriceItemResponse
) {
    companion object {
        fun from(
            brandPrice: BrandPriceDto,
            prices: GetBrandCategoryPricesDto
        ): GetBrandLowestPricesResponse {
            return GetBrandLowestPricesResponse(
                LowestPriceItemResponse(
                    brand = brandPrice.brand,
                    categories = prices.prices.map { BrandPriceItemResponse.from(it) },
                    totalPrice = prices.totalPrice
                )
            )
        }
    }
}

data class LowestPriceItemResponse(
    val brand: String,
    val categories: List<BrandPriceItemResponse>,
    val totalPrice: Long
)

data class BrandPriceItemResponse(
    val category: String,
    val productPrice: Long
) {
    companion object {
        fun from(price: BrandCategoryPriceDto): BrandPriceItemResponse {
            return BrandPriceItemResponse(
                category = price.category,
                productPrice = price.productPrice
            )
        }
    }
}
