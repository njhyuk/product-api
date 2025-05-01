package com.njhyuk.codi.core.search.domain

import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "products")
data class ProductDocument(
    @Id
    val id: Long = 0L,
    val name: String,
    val price: Long,
    val category: String,
    val brand: String
)
