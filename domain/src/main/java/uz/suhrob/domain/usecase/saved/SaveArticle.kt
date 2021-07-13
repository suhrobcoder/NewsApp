package uz.suhrob.domain.usecase.saved

import uz.suhrob.database.dao.SavedArticleDao
import uz.suhrob.domain.mapper.toDbModel
import uz.suhrob.domain.model.Article

class SaveArticle(
    private val dao: SavedArticleDao,
) {
    suspend operator fun invoke(article: Article) {
        dao.insert(article.toDbModel())
    }
}