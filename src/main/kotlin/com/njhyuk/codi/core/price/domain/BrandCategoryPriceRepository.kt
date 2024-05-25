package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BrandCategoryPriceRepository : JpaRepository<BrandCategoryPrice, Long> {
    fun findAllByBrand(brand: String): List<BrandCategoryPrice>
}
