package com.njhyuk.codi.inboud.web.api.v1.products.search

import com.njhyuk.codi.core.search.domain.ProductDocument
import io.swagger.v3.oas.annotations.media.Schema

data class SearchProductsResponse(
    @Schema(description = "상품 목록")
    val items: List<SearchProductsItemResponse>
) {
    companion object {
        fun from(documents: List<ProductDocument>): SearchProductsResponse {
            return SearchProductsResponse(documents.map { SearchProductsItemResponse.from(it) })
        }
    }
}

data class SearchProductsItemResponse(
    @Schema(description = "상품 번호", example = "123")
    val productNo: Long,
    @Schema(description = "상품 명", example = "후드")
    val name: String,
    @Schema(description = "가격", example = "1000")
    val price: Long,
    @Schema(description = "카테고리", example = "상의")
    val category: String,
    @Schema(description = "브랜드", example = "A")
    val brand: String,
) {
    companion object {
        fun from(document: ProductDocument): SearchProductsItemResponse {
            return SearchProductsItemResponse(
                productNo = document.id,
                name = document.name,
                price = document.price,
                category = document.category,
                brand = document.brand
            )
        }
    }
}
