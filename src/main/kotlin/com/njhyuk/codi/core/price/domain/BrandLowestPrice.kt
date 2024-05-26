package com.njhyuk.codi.core.price.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDateTime

@Entity
@Table(
    name = "brand_lowest_price",
    uniqueConstraints = [UniqueConstraint(columnNames = ["brand"])],
    indexes = [
        Index(name = "brand_lowest_price_idx01", columnList = "brand"),
        Index(name = "brand_lowest_price_idx02", columnList = "totalPrice")
    ]
)
data class BrandLowestPrice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val brand: String,
    var totalPrice: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
