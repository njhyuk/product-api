package com.njhyuk.codi.core.product.query

import com.njhyuk.codi.core.product.domian.Product
import java.time.LocalDateTime

data class ProductResponse(
    val id: Long,
    val name: String,
    var price: Long,
    var category: String,
    var brand: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id,
                name = product.name,
                price = product.price,
                category = product.category,
                brand = product.brand,
                createdAt = product.createdAt,
                updatedAt = product.updatedAt,
            )
        }
    }
}
