package com.njhyuk.codi.core.price.query

import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPriceRepository
import com.njhyuk.codi.fixtures.BrandCategoryLowestPriceFixture._브랜드_최저가_상의
import com.njhyuk.codi.fixtures.BrandCategoryLowestPriceFixture._브랜드_최저가_하의
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class GetBrandCategoryPricesQueryTest(
    private val brandCategoryLowestPriceRepository: BrandCategoryLowestPriceRepository = mock(),
    private val query: GetBrandCategoryPricesQuery = GetBrandCategoryPricesQuery(brandCategoryLowestPriceRepository)
) : DescribeSpec({
    beforeTest {
        given(
            brandCategoryLowestPriceRepository.findAllByBrand("A")
        ).willReturn(
            listOf(_브랜드_최저가_상의, _브랜드_최저가_하의)
        )
    }

    describe("findAllLowestCategoryPrices") {
        context("브랜드 A의 카테고리별 최저가를 조회한다") {
            val result = query.findAllLowestCategoryPrices("A")

            it("상의의 최저가는 10000원이다") {
                result.prices[0].category shouldBe "상의"
                result.prices[0].productPrice shouldBe 10000
            }

            it("하의의 최저가는 20000원이다") {
                result.prices[1].category shouldBe "하의"
                result.prices[1].productPrice shouldBe 20000
            }

            it("상의와 하의의 총 가격은 30000원이다") {
                result.totalPrice shouldBe 30000
            }
        }
    }
})
