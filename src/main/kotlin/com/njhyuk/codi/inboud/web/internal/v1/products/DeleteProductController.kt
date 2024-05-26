package com.njhyuk.codi.inboud.web.internal.v1.products

import com.njhyuk.codi.core.product.command.ProductDeleter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[Private API] 상품 관리")
@RestController
class DeleteProductController(
    private val productDeleter: ProductDeleter
) {
    @Operation(summary = "상품 삭제")
    @DeleteMapping("/internal/v1/products/{productNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduct(
        @Schema(description = "상품 번호", example = "123")
        @PathVariable
        productNo: Long
    ) {
        productDeleter.delete(productNo)
    }
}
