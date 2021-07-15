package uz.suhrob.domain.usecase.saved

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.suhrob.database.dao.SavedArticleDao
import uz.suhrob.domain.mapper.toDomainModel
import uz.suhrob.domain.model.Article

class GetAllSavedArticles(
    private val dao: SavedArticleDao,
) {
    operator fun invoke(): Flow<List<Article>> {
        return dao.getAllArticles().map { list -> list.map { entity -> entity.toDomainModel() } }
    }
}