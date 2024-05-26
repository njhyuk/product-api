package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.CategoryPrice
import com.njhyuk.codi.core.price.domain.CategoryPriceRepository
import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.fixtures.CategoryPriceFixture._상의_최고가
import com.njhyuk.codi.fixtures.CategoryPriceFixture._상의_최저가
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GetCategoryPricesQueryTest(
    private val categoryPriceRepository: CategoryPriceRepository = mock(),
    private val query: GetCategoryPricesQuery = GetCategoryPricesQuery(categoryPriceRepository)
) : DescribeSpec({
    beforeTest {
        given(categoryPriceRepository.findAllByCategory("상의"))
            .willReturn(listOf(_상의_최고가, _상의_최저가))
        given(categoryPriceRepository.findAllByPriceType(PriceType.LOWEST))
            .willReturn(listOf(_상의_최고가))
    }

    describe("findPricesByCategory 메소드는") {
        it("카테고리의 가격정보를 조회한다") {
            val result = query.findPricesByCategory("상의")

            result.totalPrice shouldBe 15000
            result.prices.size shouldBe 2
        }
    }

    describe("findPricesByPriceType 메소드는") {
        it("가격타입의 가격정보를 조회한다") {
            val result = query.findPricesByPriceType(PriceType.LOWEST)

            result.totalPrice shouldBe 10000
            result.prices.size shouldBe 1
        }
    }
})
