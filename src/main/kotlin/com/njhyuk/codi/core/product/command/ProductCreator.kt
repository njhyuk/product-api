package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.product.domian.Product
import com.njhyuk.codi.core.product.domian.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCreator(
    private val productRepository: ProductRepository
) {
    @Transactional
    fun create(command: CreateProductCommand): Long {
        val product = productRepository.save(
            Product(
                price = command.price,
                category = command.category,
                brand = command.brand
            )
        )
        return product.id
    }
}
