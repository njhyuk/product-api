package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BrandPriceRepository : JpaRepository<BrandPrice, Long> {
    fun findFirstByOrderByTotalPrice(): BrandPrice?
}
