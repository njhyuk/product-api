package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandLowestPrice
import com.njhyuk.codi.core.price.domain.BrandLowestPriceRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GetBrandLowestPriceQueryTest(
    private val brandLowestPriceRepository: BrandLowestPriceRepository = mock(),
    private val query: GetBrandLowestPriceQuery = GetBrandLowestPriceQuery(brandLowestPriceRepository)
) : DescribeSpec({

    beforeTest {
        given(brandLowestPriceRepository.findFirstByOrderByTotalPrice())
            .willReturn(
                BrandLowestPrice(
                    brand = "A",
                    totalPrice = 1000
                )
            )
    }

    describe("getLowestBrandPrice") {
        it("가장 낮은 가격의 브랜드를 조회한다") {
            val result = query.getLowestBrandPrice()

            result shouldBe BrandPriceDto(
                brand = "A",
                totalPrice = 1000
            )
        }
    }
})
