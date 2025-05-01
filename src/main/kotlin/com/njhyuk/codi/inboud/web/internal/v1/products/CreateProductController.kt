package com.njhyuk.codi.inboud.web.internal.v1.products

import com.njhyuk.codi.core.product.command.CreateProductCommand
import com.njhyuk.codi.core.product.command.ProductCreator
import com.njhyuk.codi.inboud.web.WebResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[Private API] 상품 관리")
@RestController
class CreateProductController(
    private val productCreator: ProductCreator
) {
    @Operation(summary = "상품 생성")
    @PostMapping("/internal/v1/products")
    fun createProduct(
        @RequestBody request: CreateProductRequest
    ): WebResponse<CreateProductResponse> {
        val response = productCreator.create(
            CreateProductCommand(
                price = request.price,
                category = request.category,
                brand = request.brand,
                name = request.name
            )
        )

        return WebResponse.success(
            CreateProductResponse(response)
        )
    }
}
