package com.njhyuk.codi.inboud.web.api.v1.products.search

import com.njhyuk.codi.core.search.query.GetProductDocumentSearchQuery
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[Public API] 상품 검색")
@RestController
class SearchProductsController(
    private val getProductDocumentSearchQuery: GetProductDocumentSearchQuery,
) {
    @GetMapping("/api/v1/products/search")
    fun searchProducts(
        @Schema(description = "키워드", example = "상의")
        @RequestParam keyword: String,
        @Schema(description = "페이지", example = "0")
        @RequestParam page: Int = 0,
        @Schema(description = "사이즈", example = "10")
        @RequestParam size: Int = 10,
    ): SearchProductsResponse {
        val products = getProductDocumentSearchQuery.search(
            keyword = keyword,
            page = page,
            size = size
        )

        return SearchProductsResponse.from(products)
    }
}
