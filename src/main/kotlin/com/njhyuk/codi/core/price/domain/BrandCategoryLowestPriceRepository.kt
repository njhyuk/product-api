package com.njhyuk.codi.core.price.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BrandCategoryLowestPriceRepository : JpaRepository<BrandCategoryLowestPrice, Long> {
    fun findAllByBrand(brand: String): List<BrandCategoryLowestPrice>

    @Query(
        "SELECT p FROM BrandCategoryLowestPrice p " +
            "WHERE p.brand = :brand AND p.category = :category ORDER BY p.productPrice asc"
    )
    fun findLowestPrice(
        brand: String,
        category: String
    ): BrandCategoryLowestPrice?
}
