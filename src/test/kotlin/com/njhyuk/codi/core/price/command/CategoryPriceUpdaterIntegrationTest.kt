package com.njhyuk.codi.core.price.command

import com.njhyuk.codi.core.price.domain.CategoryPriceRepository
import com.njhyuk.codi.core.price.domain.PriceType
import com.njhyuk.codi.core.product.command.CreateProductCommand
import com.njhyuk.codi.core.product.command.ProductCreator
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import mu.KotlinLogging
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

private val log = KotlinLogging.logger {}

@SpringBootTest
class CategoryPriceUpdaterIntegrationTest(
    private val productCreator: ProductCreator,
    private val categoryPriceRepository: CategoryPriceRepository
) : DescribeSpec({
    describe("상품 생성 통합 테스트는") {
        it("여러 스레드를 동시에 시작해도 최저가 1원으로 업데이트 한다") {
            val threadCount = 10
            val executorService = Executors.newFixedThreadPool(threadCount)
            val latch = CountDownLatch(threadCount)

            for (i in 1..threadCount) {
                executorService.submit {
                    try {
                        log.info { "$i 스레드 상품 생성 통합 테스트 시작" }
                        val command = CreateProductCommand(i.toLong(), "임시_카테고리", "임시_브랜드", "임시_이름")
                        productCreator.create(command)
                    } finally {
                        log.info { "$i 스레드 상품 생성 통합 테스트 종료" }
                        latch.countDown()
                    }
                }
            }

            latch.await()

            val lowestPrice = categoryPriceRepository.findLowestPrice(
                priceType = PriceType.LOWEST,
                category = "임시_카테고리"
            )

            lowestPrice!!.productPrice shouldBe 1
        }
    }
})
