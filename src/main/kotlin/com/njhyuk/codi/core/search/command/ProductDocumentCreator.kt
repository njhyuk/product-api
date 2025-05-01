package com.njhyuk.codi.core.search.command

import com.njhyuk.codi.core.search.domain.ProductDocument
import com.njhyuk.codi.core.search.domain.ProductDocumentRepository
import org.springframework.stereotype.Service

@Service
class ProductDocumentCreator(
    private val productDocumentRepository: ProductDocumentRepository,
) {
    fun create(command: CreateProductDocumentCommand) {
        productDocumentRepository.save(
            ProductDocument(
                id = command.id,
                name = command.name,
                price = command.price,
                category = command.category,
                brand = command.brand,
            )
        )
    }
}
