package com.njhyuk.codi.core.product.query

import com.njhyuk.codi.core.price.exception.NotExistsProductException
import com.njhyuk.codi.core.product.domian.ProductRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class GetProductQuery(
    private val productRepository: ProductRepository,
) {
    fun getProduct(productNo: Long): ProductResponse {
        val product = productRepository.findByIdOrNull(productNo)
            ?: throw NotExistsProductException()

        return ProductResponse.from(product)
    }
}
