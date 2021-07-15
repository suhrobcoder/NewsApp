package uz.suhrob.domain.usecase.top

import uz.suhrob.api.NewsApiService
import uz.suhrob.database.dao.TopArticleDao
import uz.suhrob.domain.mapper.toDbModel
import uz.suhrob.domain.mapper.toDomainModel
import uz.suhrob.domain.model.categories
import uz.suhrob.domain.util.PAGE_SIZE
import uz.suhrob.pref.AppPrefs
import java.util.*

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
            ).articles
            dao.clearTopArticlesByCategory(category.name)
            dao.insert(articles.map { dto -> dto.toDomainModel().toDbModel(category) })
        }
    }
}