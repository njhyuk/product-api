package com.njhyuk.codi.core.product.domian

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long> {
    fun findAllByBrand(brand: String): List<Product>
}
