package com.njhyuk.codi.core.search.command

data class CreateProductDocumentCommand(
    val id: Long,
    val name: String,
    val price: Long,
    val category: String,
    val brand: String
)
