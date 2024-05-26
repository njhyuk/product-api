package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetBrandCategoryPricesQuery(
    private val brandCategoryLowestPriceRepository: BrandCategoryLowestPriceRepository
) {
    @Transactional(readOnly = true)
    fun findAllLowestCategoryPrices(brand: String): GetBrandCategoryPricesDto {
        val prices = brandCategoryLowestPriceRepository.findAllByBrand(brand)
            .map { BrandCategoryPriceDto.from(it) }

        return GetBrandCategoryPricesDto(
            prices = prices,
            totalPrice = prices.sumOf { it.productPrice }
        )
    }
}
