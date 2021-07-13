package uz.suhrob.domain.usecase.top

import uz.suhrob.database.dao.TopArticleDao
import uz.suhrob.domain.mapper.toDbModel
import uz.suhrob.domain.model.Article
import uz.suhrob.domain.model.Category

class InsertTopArticles(
    private val dao: TopArticleDao,
) {
    suspend operator fun invoke(articles: List<Article>, category: Category) {
        dao.insert(articles.map { article -> article.toDbModel(category) })
    }
}