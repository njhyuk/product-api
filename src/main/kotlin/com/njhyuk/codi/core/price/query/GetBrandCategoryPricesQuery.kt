package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandCategoryPriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetBrandCategoryPricesQuery(
    private val brandCategoryPriceRepository: BrandCategoryPriceRepository
) {
    @Transactional(readOnly = true)
    fun findAllLowestCategoryPrices(brand: String): GetBrandCategoryPricesDto {
        val prices = brandCategoryPriceRepository.findAllByBrand(brand)
            .map { BrandCategoryPriceDto.from(it) }

        return GetBrandCategoryPricesDto(
            prices = prices,
            totalPrice = prices.sumOf { it.productPrice }
        )
    }
}
