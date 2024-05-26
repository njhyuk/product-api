package com.njhyuk.codi.core.price.command

import com.njhyuk.codi.core.price.domain.CategoryPrice
import com.njhyuk.codi.core.price.domain.CategoryPriceRepository
import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.price.domain.PriceType.HIGHEST
import com.njhyuk.codi.core.price.domain.PriceType.LOWEST
import com.njhyuk.codi.core.product.domian.Product
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.extension.lock.RedisDistributedLock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryPriceUpdater(
    private val productRepository: ProductRepository,
    private val categoryPriceRepository: CategoryPriceRepository
) {
    @Transactional
    @RedisDistributedLock(key = "categoryPriceUpdate")
    fun updatePrice(category: String) {
        PriceType.values().forEach { priceType ->
            val product = findProduct(category, priceType) ?: return

            updatePrice(category, product, priceType)
        }
    }

    private fun findProduct(category: String, priceType: PriceType): Product? {
        return when (priceType) {
            LOWEST -> productRepository.findFirstByCategoryOrderByPriceAsc(category)
            HIGHEST -> productRepository.findFirstByCategoryOrderByPriceDesc(category)
        }
    }

    private fun updatePrice(category: String, product: Product, priceType: PriceType) {
        val categoryPrice = findCategoryPrice(category, priceType)

        if (shouldUpdatePrice(categoryPrice, product.price, priceType)) {
            val newPrice = categoryPrice ?: CategoryPrice(
                category = category,
                brand = product.brand,
                productNo = product.id,
                productPrice = product.price,
                priceType = priceType
            )

            newPrice.apply {
                this.category = category
                this.brand = product.brand
                this.productNo = product.id
                this.productPrice = product.price
            }

            categoryPriceRepository.save(newPrice)
        }
    }

    private fun findCategoryPrice(category: String, priceType: PriceType): CategoryPrice? {
        return when (priceType) {
            LOWEST -> categoryPriceRepository.findLowestPrice(priceType, category)
            HIGHEST -> categoryPriceRepository.findHighestPrice(priceType, category)
        }
    }

    private fun shouldUpdatePrice(oldPrice: CategoryPrice?, newPrice: Long, priceType: PriceType): Boolean {
        if (oldPrice == null) return true

        return when (priceType) {
            LOWEST -> oldPrice.productPrice > newPrice
            HIGHEST -> oldPrice.productPrice < newPrice
        }
    }
}
