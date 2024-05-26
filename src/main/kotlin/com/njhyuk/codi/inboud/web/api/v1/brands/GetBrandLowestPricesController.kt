package com.njhyuk.codi.inboud.web.api.v1.brands

import com.njhyuk.codi.core.price.query.GetBrandCategoryPricesQuery
import com.njhyuk.codi.core.price.query.GetBrandLowestPriceQuery
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetBrandLowestPricesController(
    private val getBrandLowestPriceQuery: GetBrandLowestPriceQuery,
    private val getBrandCategoryPricesQuery: GetBrandCategoryPricesQuery
) {
    @GetMapping("/api/v1/brands/lowest-prices")
    fun getBrandLowestPrices(): WebResponse<GetBrandLowestPricesResponse?> {
        val lowestBrandPrice = getBrandLowestPriceQuery.getLowestBrandPrice()

        val brandCategoryPrices = lowestBrandPrice?.let {
            getBrandCategoryPricesQuery.findAllLowestCategoryPrices(it.brand)
        }

        return WebResponse.success(
            brandCategoryPrices?.let {
                GetBrandLowestPricesResponse.of(lowestBrandPrice.brand, it)
            }
        )
    }
}
