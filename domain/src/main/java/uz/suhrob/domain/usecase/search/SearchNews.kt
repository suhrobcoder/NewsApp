package uz.suhrob.domain.usecase.search

import uz.suhrob.api.NewsApiService
import uz.suhrob.domain.mapper.toDomainModel
import uz.suhrob.domain.model.Article
import uz.suhrob.domain.util.PAGE_SIZE
import uz.suhrob.domain.util.Resource
import uz.suhrob.domain.util.safeCall

class SearchNews(
    private val api: NewsApiService,
) {
    suspend operator fun invoke(query: String, page: Int): Resource<List<Article>> {
        return safeCall {
            val response = api.getEverything(query = query, pageSize = PAGE_SIZE, page = page)
            return Resource.Success(response.body()!!.articles.map { dto -> dto.toDomainModel() })
        }
    }
}