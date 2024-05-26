package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BrandLowestPriceRepository : JpaRepository<BrandLowestPrice, Long> {
    fun findByBrand(brand: String): BrandLowestPrice?

    fun findFirstByOrderByTotalPrice(): BrandLowestPrice?
}
