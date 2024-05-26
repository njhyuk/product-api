package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryPriceRepository : JpaRepository<CategoryPrice, Long> {
    fun findAllByPriceType(priceType: PriceType): List<CategoryPrice>

    fun findAllByCategory(category: String): List<CategoryPrice>

    @Query(
        "SELECT cp FROM CategoryPrice cp " +
                "WHERE cp.priceType = :priceType AND cp.category = :category ORDER BY cp.productPrice asc"
    )
    fun findLowestPrice(
        priceType: PriceType,
        category: String
    ): CategoryPrice?

    @Query(
        "SELECT cp FROM CategoryPrice cp " +
                "WHERE cp.priceType = :priceType AND cp.category = :category ORDER BY cp.productPrice desc"
    )
    fun findHighestPrice(
        priceType: PriceType,
        category: String
    ): CategoryPrice?
}
