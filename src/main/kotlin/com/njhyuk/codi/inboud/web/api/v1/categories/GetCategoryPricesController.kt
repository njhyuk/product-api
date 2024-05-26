package com.njhyuk.codi.inboud.web.api.v1.categories

import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.price.query.GetCategoryPricesQuery
import com.njhyuk.codi.inboud.web.WebResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[Public API] 카테고리")
@RestController
class GetCategoryPricesController(
    private val getCategoryPricesQuery: GetCategoryPricesQuery
) {
    @Operation(summary = "카테고리 최저가/최고가 조회")
    @GetMapping("/api/v1/categories/prices")
    fun getCategoryPrices(
        @Schema(description = "카테고리", example = "상의")
        @RequestParam category: String
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
