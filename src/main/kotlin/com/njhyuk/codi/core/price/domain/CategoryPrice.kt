package com.njhyuk.codi.core.price.domain

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.LocalDateTime

@Entity
@Table(
    name = "category_price",
    uniqueConstraints = [UniqueConstraint(columnNames = ["priceType", "category"])],
    indexes = [
        Index(name = "category_price_idx01", columnList = "priceType, category, productPrice")
    ]
)
data class CategoryPrice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Enumerated(EnumType.STRING)
    val priceType: PriceType,
    var category: String,
    var brand: String,
    var productNo: Long,
    var productPrice: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
