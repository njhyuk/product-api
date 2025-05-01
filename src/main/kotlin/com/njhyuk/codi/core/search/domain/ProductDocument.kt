package com.njhyuk.codi.core.search.domain

import jakarta.persistence.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.time.LocalDateTime

@Document(indexName = "products")
data class ProductDocument(
    @Id
    val id: Long = 0L,
    val name: String,
    val price: Long,
    val category: String,
    val brand: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
