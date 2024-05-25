package com.njhyuk.codi.inboud.web.api.v1.brands

import com.njhyuk.codi.core.price.query.GetBrandCategoryPricesQuery
import com.njhyuk.codi.core.price.query.GetLowestBrandPriceQuery
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetBrandLowestPricesController(
    private val getLowestBrandPriceQuery: GetLowestBrandPriceQuery,
    private val getBrandCategoryPricesQuery: GetBrandCategoryPricesQuery
) {
    @GetMapping("/api/v1/brands/lowest-prices")
    fun getBrandLowestPrices(): WebResponse<GetBrandLowestPricesResponse?> {
        val lowestBrandPrice = getLowestBrandPriceQuery.getLowestBrandPrice()

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
