package com.njhyuk.codi.core.product.domian

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findFirstByCategoryOrderByPriceAsc(category: String): Product?

    fun findFirstByCategoryOrderByPriceDesc(category: String): Product?

    fun findFirstByBrandAndCategoryOrderByPriceAsc(brand: String, category: String): Product?
}
