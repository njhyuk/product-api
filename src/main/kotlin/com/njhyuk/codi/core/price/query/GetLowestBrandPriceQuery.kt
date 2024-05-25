package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandPriceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetLowestBrandPriceQuery(
    private val brandPriceRepository: BrandPriceRepository
) {
    @Transactional(readOnly = true)
    fun getLowestBrandPrice(): BrandPriceDto? {
        return brandPriceRepository.findFirstByOrderByTotalPrice()
            ?.let { BrandPriceDto.from(it) }
    }
}
