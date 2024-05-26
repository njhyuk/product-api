package com.njhyuk.codi.core.price.command

import com.njhyuk.codi.core.price.domain.CategoryPriceRepository
import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.fixtures.CategoryPriceFixture
import com.njhyuk.codi.fixtures.ProductFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class CategoryPriceUpdaterTest(
    private val productRepository: ProductRepository = mock(),
    private val categoryPriceRepository: CategoryPriceRepository = mock(),
    private val categoryPriceUpdater: CategoryPriceUpdater = CategoryPriceUpdater(
        productRepository,
        categoryPriceRepository
    )
) : DescribeSpec({
    val _업데이트_이전_최저가 = CategoryPriceFixture._상의_최고가.copy()
    val _업데이트_이전_최고가 = CategoryPriceFixture._상의_최저가.copy()

    val _새로운_최저가 = ProductFixture._상의_A브랜드
    val _새로운_최고가 = ProductFixture._상의_B브랜드

    beforeTest {
        given(productRepository.findFirstByCategoryOrderByPriceAsc("상의"))
            .willReturn(_새로운_최저가)
        given(productRepository.findFirstByCategoryOrderByPriceDesc("상의"))
            .willReturn(_새로운_최고가)
        given(categoryPriceRepository.findLowestPrice(PriceType.LOWEST, "상의"))
            .willReturn(_업데이트_이전_최저가)
        given(categoryPriceRepository.findHighestPrice(PriceType.HIGHEST, "상의"))
            .willReturn(_업데이트_이전_최고가)
    }

    describe("updatePrice 메서드는") {
        it("카테고리의 최저가와 최고가를 업데이트 한다") {
            categoryPriceUpdater.updatePrice("상의")

            _업데이트_이전_최저가.productPrice shouldBe _새로운_최저가.price
            _업데이트_이전_최고가.productPrice shouldBe _새로운_최고가.price
        }
    }
})
