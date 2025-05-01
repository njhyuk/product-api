package com.njhyuk.codi.inboud.consumer

import com.njhyuk.codi.core.price.command.BrandPriceUpdater
import com.njhyuk.codi.core.price.command.CategoryPriceUpdater
import com.njhyuk.codi.core.product.event.ProductCreatedEvent
import com.njhyuk.codi.core.product.event.ProductEvent
import com.njhyuk.codi.core.product.query.GetProductQuery
import com.njhyuk.codi.core.search.command.CreateProductDocumentCommand
import com.njhyuk.codi.core.search.command.ProductDocumentCreator
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {}

@Component
class ProductEventConsumer(
    private val getProductQuery: GetProductQuery,
    private val productDocumentCreator: ProductDocumentCreator,
    private val categoryPriceUpdater: CategoryPriceUpdater,
    private val brandPriceUpdater: BrandPriceUpdater
) {
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleProductEvent(event: ProductEvent) {
        log.info { "ProductEventConsumer : $event" }

        val product = getProductQuery.getProduct(event.productNo)

        categoryPriceUpdater.updatePrice(event.category)
        brandPriceUpdater.updatePrice(event.brand, event.category)

        if (event is ProductCreatedEvent) {
            productDocumentCreator.create(
                CreateProductDocumentCommand(
                    id = product.id,
                    name = product.name,
                    price = product.price,
                    category = product.category,
                    brand = product.brand
                )
            )
        }
    }
}
