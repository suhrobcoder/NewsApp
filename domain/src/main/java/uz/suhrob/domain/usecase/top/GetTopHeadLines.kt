package uz.suhrob.domain.usecase.top

import uz.suhrob.api.NewsApiService
import uz.suhrob.domain.mapper.toDomainModel
import uz.suhrob.domain.model.Article
import uz.suhrob.domain.model.Category
import uz.suhrob.domain.util.PAGE_SIZE
import uz.suhrob.domain.util.Resource
import uz.suhrob.domain.util.safeCall

class GetTopHeadLines(
    private val api: NewsApiService,
) {
    suspend operator fun invoke(category: Category, page: Int): Resource<List<Article>> {
        return safeCall {
            val response = api.getTopHeadlines(category = category.name, pageSize = PAGE_SIZE, page = page)
            return Resource.Success(response.body()!!.articles.map { dto -> dto.toDomainModel() })
        }
    }
}