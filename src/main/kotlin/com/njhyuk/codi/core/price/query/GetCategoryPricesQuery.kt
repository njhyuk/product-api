package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.CategoryPriceRepository
import com.njhyuk.codi.core.price.domain.PriceType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetCategoryPricesQuery(
    private val categoryPriceRepository: CategoryPriceRepository
) {
    @Transactional(readOnly = true)
    fun findAllByPriceType(priceType: PriceType): GetCategoryPricesDto {
        val prices = categoryPriceRepository.findAllByPriceType(priceType)
            .map { CategoryPriceDto.from(it) }

        return GetCategoryPricesDto(
            prices = prices,
            totalPrice = prices.sumOf { it.productPrice }
        )
    }
}
