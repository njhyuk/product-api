package com.njhyuk.codi.inboud.web.api.v1.brands

import com.njhyuk.codi.core.price.query.BrandCategoryPriceDto
import com.njhyuk.codi.core.price.query.GetBrandCategoryPricesDto
import com.njhyuk.codi.inboud.web.api.v1.brands.BrandPriceItemResponse as BrandPriceItem
import com.njhyuk.codi.inboud.web.api.v1.brands.BrandLowestPriceItemResponse as PriceItem

data class GetBrandLowestPricesResponse(
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
    val brand: String,
    val categories: List<BrandPriceItem>,
    val totalPrice: Long
)

data class BrandPriceItemResponse(
    val category: String,
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
