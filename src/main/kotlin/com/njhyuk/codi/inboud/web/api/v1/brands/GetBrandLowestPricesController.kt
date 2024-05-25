package com.njhyuk.codi.inboud.web.api.v1.brands

import com.njhyuk.codi.core.price.query.GetBrandCategoryPricesQuery
import com.njhyuk.codi.core.price.query.GetBrandPriceQuery
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetBrandLowestPricesController(
    private val getBrandPriceQuery: GetBrandPriceQuery,
    private val getBrandCategoryPricesQuery: GetBrandCategoryPricesQuery
) {
    @GetMapping("/api/v1/codi/brands/lowest-prices")
    fun getBrandLowestPrices(): WebResponse<GetBrandLowestPricesResponse> {
        val brandPrice = getBrandPriceQuery.findLowestPrice()
            ?: throw IllegalStateException("Brand price not found")

        val brandCategoryPrices = getBrandCategoryPricesQuery.findLowestPrices(brandPrice.brand)

        return WebResponse.success(
            GetBrandLowestPricesResponse.from(brandPrice, brandCategoryPrices)
        )
    }
}
