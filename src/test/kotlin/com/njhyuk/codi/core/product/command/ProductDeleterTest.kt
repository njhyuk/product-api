package com.njhyuk.codi.core.product.command

import com.njhyuk.codi.core.price.exception.NotExistsProductException
import com.njhyuk.codi.core.product.domian.ProductRepository
import com.njhyuk.codi.fixtures.ProductFixture
import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.springframework.context.ApplicationEventPublisher
import java.util.*

class ProductDeleterTest(
    private val productRepository: ProductRepository = mock(),
    private val applicationEventPublisher: ApplicationEventPublisher = mock(),
    private val productDeleter: ProductDeleter = ProductDeleter(
        productRepository = productRepository,
        applicationEventPublisher = applicationEventPublisher
    )
) : DescribeSpec({
    beforeTest {
        given(productRepository.findById(1)).willReturn(Optional.of(ProductFixture._상의_A브랜드))
        given(productRepository.findById(2)).willReturn(Optional.empty())
    }

    describe("delete 메서드는") {
        context("상품이 존재하는 경우") {
            it("상품을 삭제한다") {
                productDeleter.delete(1)
            }
        }
        context("상품이 존재하지 않는 경우") {
            it("NotExistsProductException 예외를 던진다") {
                assertThrows<NotExistsProductException> {
                    productDeleter.delete(2)
                }
            }
        }
    }
})
