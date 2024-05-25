package com.njhyuk.codi.inboud.web.internal.v1.products

import com.njhyuk.codi.core.product.command.ProductUpdater
import com.njhyuk.codi.core.product.command.UpdateProductCommand
import com.njhyuk.codi.inboud.web.WebResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateProductController(
    private val productUpdater: ProductUpdater
) {
    @PutMapping("/internal/v1/products/{productNo}")
    fun updateProduct(
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
