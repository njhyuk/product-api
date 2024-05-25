package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.price.exception.NotExistsProductException
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.core.product.event.ProductDeletedEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductDeleter(
    private val productRepository: ProductRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    @Transactional
    fun delete(productNo: Long) {
        val product = productRepository.findByIdOrNull(productNo)
            ?: throw NotExistsProductException()

        applicationEventPublisher.publishEvent(
            ProductDeletedEvent(
                productNo = product.id,
                category = product.category,
                brand = product.brand,
            )
        )

        productRepository.delete(product)
    }
}
