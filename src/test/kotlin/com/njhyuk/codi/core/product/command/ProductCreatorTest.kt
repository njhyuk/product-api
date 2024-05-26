package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.product.domian.Product
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.fixtures.ProductFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.springframework.context.ApplicationEventPublisher

class ProductCreatorTest(
    private val productRepository: ProductRepository = mock(),
    private val applicationEventPublisher: ApplicationEventPublisher = mock(),
    private val productCreator: ProductCreator = ProductCreator(productRepository, applicationEventPublisher)
) : DescribeSpec({
    beforeTest {
        given(productRepository.save(any<Product>()))
            .willReturn(ProductFixture._상의)
    }

    describe("create 메서드는") {
        it("상품을 생성한다") {
            val result = productCreator.create(
                CreateProductCommand(
                    price = 1000,
                    category = "상의",
                    brand = "A"
                )
            )

            result shouldBe 1
        }
    }
})
