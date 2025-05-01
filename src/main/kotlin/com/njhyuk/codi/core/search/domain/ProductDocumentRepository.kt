package com.njhyuk.codi.core.search.domain

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface ProductDocumentRepository : ElasticsearchRepository<ProductDocument, Long>
