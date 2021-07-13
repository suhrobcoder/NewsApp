package uz.suhrob.domain.usecase.top

import uz.suhrob.api.NewsApiService
import uz.suhrob.database.dao.TopArticleDao
import uz.suhrob.domain.model.categories
import uz.suhrob.domain.util.PAGE_SIZE

class UpdateTopArticles(
    private val dao: TopArticleDao,
    private val api: NewsApiService,
) {
    suspend operator fun invoke() {
        categories.forEach { category ->
            val articles = api.getTopHeadlines(
                category = category.name,
                pageSize = PAGE_SIZE,
                page = 1
            )

        }
    }
}