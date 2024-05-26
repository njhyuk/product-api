package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BrandLowestPriceRepository : JpaRepository<BrandLowestPrice, Long> {
    fun findFirstByOrderByTotalPrice(): BrandLowestPrice?
}
