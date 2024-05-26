package com.njhyuk.codi.core.price.command

import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPrice
import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPriceRepository
import com.njhyuk.codi.core.price.domain.BrandLowestPrice
import com.njhyuk.codi.core.price.domain.BrandLowestPriceRepository
import com.njhyuk.codi.core.product.domian.Product
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.extension.lock.RedisDistributedLock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BrandPriceUpdater(
    private val productRepository: ProductRepository,
    private val brandLowestPriceRepository: BrandLowestPriceRepository,
    private val brandCategoryLowestPriceRepository: BrandCategoryLowestPriceRepository
) {
    @Transactional
    @RedisDistributedLock(key = "updatePrice")
    fun updatePrice(brand: String, category: String) {
        val product = productRepository.findFirstByBrandAndCategoryOrderByPriceAsc(brand, category)
            ?: return

        val categoryPrice = brandCategoryLowestPriceRepository.findLowestPrice(brand, category)

        updateBrandCategoryPrice(categoryPrice, product, category, brand)
        updateBrandTotalPrice(brand)
    }

    private fun updateBrandCategoryPrice(
        categoryPrice: BrandCategoryLowestPrice?,
        product: Product,
        category: String,
        brand: String
    ) {
        val newPrice = categoryPrice ?: BrandCategoryLowestPrice(
            category = category,
            brand = brand,
            productNo = product.id,
            productPrice = product.price
        )

        newPrice.apply {
            productNo = product.id
            productPrice = product.price
        }

        brandCategoryLowestPriceRepository.saveAndFlush(newPrice)
    }

    private fun updateBrandTotalPrice(brand: String) {
        val totalCategoryPrice = brandCategoryLowestPriceRepository.findAllByBrand(brand)
            .sumOf { it.productPrice }
        val brandLowestPrice = brandLowestPriceRepository.findByBrand(brand)
            ?: BrandLowestPrice(
                brand = brand,
                totalPrice = totalCategoryPrice
            )

        brandLowestPrice.apply {
            totalPrice = totalCategoryPrice
        }

        brandLowestPriceRepository.save(brandLowestPrice)
    }
}
