package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.price.query.GetCategoryPricesQuery
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetCategoryAllPricesController(
    private val getCategoryPricesQuery: GetCategoryPricesQuery
) {
    @GetMapping("/api/v1/categories/{category}/prices")
    fun getCategoryAllPrices(
        @PathVariable category: String
    ): WebResponse<GetCategoryAllPricesResponse> {
        val response = getCategoryPricesQuery.findAllByCategory(category)
            .prices.associateBy { it.priceType }

        val lowestPrice = response[PriceType.LOWEST]
        val highestPrice = response[PriceType.LOWEST]

        return WebResponse.success(
            GetCategoryAllPricesResponse.from(
                lowestDto = lowestPrice,
                highestDto = highestPrice
            )
        )
    }
}
