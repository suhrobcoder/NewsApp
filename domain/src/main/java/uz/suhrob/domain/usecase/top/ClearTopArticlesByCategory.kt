package uz.suhrob.domain.usecase.top

import uz.suhrob.database.dao.TopArticleDao
import uz.suhrob.domain.model.Category

class ClearTopArticlesByCategory(
    private val dao: TopArticleDao,
) {
    suspend operator fun invoke(category: Category) {
        dao.clearTopArticlesByCategory(category.name)
    }
}