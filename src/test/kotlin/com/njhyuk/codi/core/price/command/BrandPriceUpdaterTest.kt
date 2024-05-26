package com.njhyuk.codi.core.price.command

import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPrice
import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPriceRepository
import com.njhyuk.codi.core.price.domain.BrandLowestPrice
import com.njhyuk.codi.core.price.domain.BrandLowestPriceRepository
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.fixtures.BrandCategoryLowestPriceFixture._브랜드_최저가_상의
import com.njhyuk.codi.fixtures.BrandCategoryLowestPriceFixture._브랜드_최저가_하의
import com.njhyuk.codi.fixtures.ProductFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class BrandPriceUpdaterTest(
    private val productRepository: ProductRepository = mock(),
    private val brandLowestPriceRepository: BrandLowestPriceRepository = mock(),
    private val brandCategoryLowestPriceRepository: BrandCategoryLowestPriceRepository = mock(),
    private val brandPriceUpdater: BrandPriceUpdater = BrandPriceUpdater(
        productRepository,
        brandLowestPriceRepository,
        brandCategoryLowestPriceRepository
    )
) : DescribeSpec({
    val _기존_최저가_브랜드 = BrandLowestPrice(
        brand = "A",
        totalPrice = 9999999
    )
    val 기존_A브랜드_최저가_상의 = BrandCategoryLowestPrice(
        id = 1,
        brand = "A",
        category = "상의",
        productPrice = 999999,
        productNo = 1
    )

    beforeTest {
        given(productRepository.findFirstByBrandAndCategoryOrderByPriceAsc("A", "상의"))
            .willReturn(ProductFixture._상의_A브랜드)
        given(brandLowestPriceRepository.findByBrand("A"))
            .willReturn(_기존_최저가_브랜드)
        given(brandCategoryLowestPriceRepository.findLowestPrice("A", "상의"))
            .willReturn(기존_A브랜드_최저가_상의)
        given(brandCategoryLowestPriceRepository.findAllByBrand("A"))
            .willReturn(
                listOf(
                    _브랜드_최저가_상의,
                    _브랜드_최저가_하의
                )
            )
    }

    describe("updatePrice 메소드는") {
        brandPriceUpdater.updatePrice("A", "상의")

        it("브랜드 카테고리별 최저가를 업데이트한다") {
            기존_A브랜드_최저가_상의.productPrice shouldBe ProductFixture._상의_A브랜드.price
        }

        it("브랜드의 최저가 총액을 업데이트한다") {
            _기존_최저가_브랜드.totalPrice shouldBe (_브랜드_최저가_상의.productPrice + _브랜드_최저가_하의.productPrice)
        }
    }
})
