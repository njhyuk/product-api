package com.njhyuk.codi.fixtures

import com.njhyuk.codi.core.price.domain.BrandCategoryLowestPrice

object BrandCategoryLowestPriceFixture {
    val _브랜드_최저가_상의 = BrandCategoryLowestPrice(
        id = 1,
        brand = "A",
        category = "상의",
        productPrice = 10000,
        productNo = 1
    )
    val _브랜드_최저가_하의 = BrandCategoryLowestPrice(
        id = 2,
        brand = "A",
        category = "하의",
        productPrice = 20000,
        productNo = 2
    )
}
