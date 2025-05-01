package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.product.domian.Product
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.fixtures.ProductFixture
import com.njhyuk.codi.outbound.event.EventPublisher
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

class ProductCreatorTest(
    private val productRepository: ProductRepository = mock(),
    private val eventPublisher: EventPublisher = mock(),
    private val productCreator: ProductCreator = ProductCreator(productRepository, eventPublisher)
) : DescribeSpec({
    beforeTest {
        given(productRepository.save(any<Product>()))
            .willReturn(ProductFixture._상의_A브랜드)
    }

    describe("create 메서드는") {
        it("상품을 생성한다") {
            val result = productCreator.create(
                CreateProductCommand(
                    price = 1000,
                    category = "상의",
                    brand = "A",
                    name = "이름",
                )
            )

            result shouldBe 1
        }
    }
})
