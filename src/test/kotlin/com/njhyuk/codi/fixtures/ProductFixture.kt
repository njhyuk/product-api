package com.njhyuk.codi.fixtures

import com.njhyuk.codi.core.product.domian.Product

object ProductFixture {
    val _상의_A브랜드 = Product(
        id = 1,
        price = 1000,
        category = "상의",
        brand = "A",
        name = "블랙 후드",
    )
    val _상의_B브랜드 = Product(
        id = 1,
        price = 7000,
        category = "상의",
        brand = "B",
        name = "그레이 후드",
    )
}
