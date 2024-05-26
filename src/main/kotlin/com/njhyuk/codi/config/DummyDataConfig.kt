package com.njhyuk.codi.config

import com.njhyuk.codi.core.product.command.CreateProductCommand
import com.njhyuk.codi.core.product.command.ProductCreator
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DummyDataConfig(
    private val productCreator: ProductCreator
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val brands = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I")
        val categories = listOf("상의", "아우터", "바지", "스니커즈", "가방", "모자", "양말", "액세서리")
        val prices = listOf(
            listOf(11200L, 5500L, 4200L, 9000L, 2000L, 1700L, 1800L, 2300L),
            listOf(10500L, 5900L, 3800L, 9100L, 2100L, 2000L, 2000L, 2200L),
            listOf(10000L, 6200L, 3300L, 9200L, 2200L, 1900L, 2200L, 2100L),
            listOf(10100L, 5100L, 3000L, 9500L, 2500L, 1500L, 2400L, 2000L),
            listOf(10700L, 5000L, 3800L, 9900L, 2300L, 1800L, 2100L, 2100L),
            listOf(11200L, 7200L, 4000L, 9300L, 2100L, 1600L, 2300L, 1900L),
            listOf(10500L, 5800L, 3900L, 9000L, 2200L, 1700L, 2100L, 2000L),
            listOf(10800L, 6300L, 3100L, 9700L, 2100L, 1600L, 2000L, 2000L),
            listOf(11400L, 6700L, 3200L, 9500L, 2400L, 1700L, 1700L, 2400L)
        )

        for ((brandIndex, brand) in brands.withIndex()) {
            for ((categoryIndex, category) in categories.withIndex()) {
                val price = prices[brandIndex][categoryIndex]
                val command = CreateProductCommand(price, category, brand)
                productCreator.create(command)
            }
        }
    }
}
