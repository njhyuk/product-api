package com.njhyuk.codi.inboud.web.internal.v1.products

import com.njhyuk.codi.core.product.command.CreateProductCommand
import com.njhyuk.codi.core.product.command.ProductCreator
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateProductController(
    private val productCreator: ProductCreator
) {
    @PostMapping("/internal/v1/products")
    fun createProduct(
        @RequestBody request: CreateProductRequest
    ): WebResponse<CreateProductResponse> {
        val response = productCreator.create(
            CreateProductCommand(
                price = request.price,
                category = request.category,
                brand = request.brand
            )
        )

        return WebResponse.success(
            CreateProductResponse(response)
        )
    }
}
