package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.price.query.GetCategoryPricesQuery
import com.njhyuk.codi.inboud.web.WebResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[Public API] 카테고리")
@RestController
class GetCategoryLowestPricesController(
    private val getCategoryPricesQuery: GetCategoryPricesQuery
) {
    @Operation(summary = "카테고리별 최저가 조회")
    @GetMapping("/api/v1/categories/lowest-prices")
    fun getCategoryLowestPrices(): WebResponse<GetCategoryLowestPricesResponse> {
        val response = getCategoryPricesQuery.findPricesByPriceType(PriceType.LOWEST)

        return WebResponse.success(
            GetCategoryLowestPricesResponse.from(response)
        )
    }
}
