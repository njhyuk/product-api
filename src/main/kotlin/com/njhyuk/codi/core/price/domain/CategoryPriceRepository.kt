package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryPriceRepository : JpaRepository<CategoryPrice, Long> {
    fun findAllByPriceType(priceType: PriceType): List<CategoryPrice>

    fun findAllByCategory(category: String): List<CategoryPrice>
}
