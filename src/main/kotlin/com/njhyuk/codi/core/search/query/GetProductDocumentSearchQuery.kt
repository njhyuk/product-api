package com.njhyuk.codi.core.search.query

import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders
import com.njhyuk.codi.core.search.domain.ProductDocument
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Service

@Service
class GetProductDocumentSearchQuery(
    private val elasticsearchOperations: ElasticsearchOperations
) {
    fun search(keyword: String, page: Int, size: Int): List<ProductDocument> {
        val query = NativeQuery.builder()
            .withQuery(
                QueryBuilders.multiMatch {
                    it.fields("name", "category", "brand")
                        .query(keyword)
                }
            )
            .withPageable(PageRequest.of(page, size))
            .build()

        val result = elasticsearchOperations.search(query, ProductDocument::class.java)

        return result.searchHits.map { it.content }
    }
}
