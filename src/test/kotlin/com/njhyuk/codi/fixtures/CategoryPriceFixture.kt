package com.njhyuk.codi.fixtures

import com.njhyuk.codi.core.price.domain.CategoryPrice
import com.njhyuk.codi.core.price.domain.PriceType

object CategoryPriceFixture {
    val _상의_최고가 = CategoryPrice(
        priceType = PriceType.HIGHEST,
        category = "상의",
        brand = "Z",
        productNo = 1,
        productPrice = 10000
    )
    val _상의_최저가 = CategoryPrice(
        priceType = PriceType.HIGHEST,
        category = "상의",
        brand = "Z",
        productNo = 1,
        productPrice = 5000
    )
}
