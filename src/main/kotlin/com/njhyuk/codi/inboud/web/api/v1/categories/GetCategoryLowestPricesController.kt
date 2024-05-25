package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.price.query.GetCategoryPricesQuery
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetCategoryLowestPricesController(
    private val getCategoryPricesQuery: GetCategoryPricesQuery
) {
    @GetMapping("/api/v1/categories/lowest-prices")
    fun getCategoryLowestPrices(): WebResponse<GetCategoryLowestPricesResponse> {
        val response = getCategoryPricesQuery.findPricesByPriceType(PriceType.LOWEST)

        return WebResponse.success(
            GetCategoryLowestPricesResponse.from(response)
        )
    }
}
