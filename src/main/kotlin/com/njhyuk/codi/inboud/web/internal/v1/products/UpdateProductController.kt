package com.njhyuk.codi.inboud.web.internal.v1.products

import com.njhyuk.codi.core.product.command.ProductUpdater
import com.njhyuk.codi.core.product.command.UpdateProductCommand
import com.njhyuk.codi.inboud.web.WebResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[Private API] 상품 관리")
@RestController
class UpdateProductController(
    private val productUpdater: ProductUpdater
) {
    @Operation(summary = "상품 수정")
    @PutMapping("/internal/v1/products/{productNo}")
    fun updateProduct(
        @Schema(description = "상품 번호", example = "123")
        @PathVariable productNo: Long,
        @RequestBody request: UpdateProductRequest
    ): WebResponse<UpdateProductResponse> {
        val response = productUpdater.update(
            UpdateProductCommand(
                productNo = productNo,
                price = request.price,
                category = request.category,
                brand = request.brand
            )
        )

        return WebResponse.success(
            UpdateProductResponse(response)
        )
    }
}
