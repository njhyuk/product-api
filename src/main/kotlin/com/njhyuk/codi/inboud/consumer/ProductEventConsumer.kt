package com.njhyuk.codi.inboud.consumer

import com.njhyuk.codi.core.price.command.BrandPriceUpdater
import com.njhyuk.codi.core.price.command.CategoryPriceUpdater
import com.njhyuk.codi.core.product.event.ProductEvent
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

private val log = KotlinLogging.logger {}

@Component
class ProductEventConsumer(
    private val categoryPriceUpdater: CategoryPriceUpdater,
    private val brandPriceUpdater: BrandPriceUpdater
) {
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleProductEvent(event: ProductEvent) {
        log.info { "ProductEventConsumer : $event" }

        categoryPriceUpdater.updatePrice(event.category)
        brandPriceUpdater.updatePrice(event.brand, event.category)
    }
}
