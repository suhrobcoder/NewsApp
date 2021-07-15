package uz.suhrob.domain.usecase.saved

import uz.suhrob.database.dao.SavedArticleDao

class DeleteSavedArticle(
    private val dao: SavedArticleDao,
) {
    suspend operator fun invoke(id: Int) {
        dao.delete(id)
    }
}