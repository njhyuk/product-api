package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.price.query.GetCategoryPricesQuery
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetCategoryPricesController(
    private val getCategoryPricesQuery: GetCategoryPricesQuery
) {
    @GetMapping("/api/v1/categories/{category}/prices")
    fun getCategoryPrices(
        @PathVariable category: String
    ): WebResponse<GetCategoryPricesResponse> {
        val response = getCategoryPricesQuery.findPricesByCategory(category)
            .prices.associateBy { it.priceType }

        val lowestPrice = response[PriceType.LOWEST]
        val highestPrice = response[PriceType.HIGHEST]

        return WebResponse.success(
            GetCategoryPricesResponse.from(
                lowestDto = lowestPrice,
                highestDto = highestPrice
            )
        )
    }
}
