package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandLowestPriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetBrandLowestPriceQuery(
    private val brandLowestPriceRepository: BrandLowestPriceRepository
) {
    @Transactional(readOnly = true)
    fun getLowestBrandPrice(): BrandPriceDto? {
        return brandLowestPriceRepository.findFirstByOrderByTotalPrice()
            ?.let { BrandPriceDto.from(it) }
    }
}
