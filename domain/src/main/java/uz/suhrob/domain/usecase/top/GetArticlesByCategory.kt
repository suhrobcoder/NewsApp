package uz.suhrob.domain.usecase.top

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.suhrob.database.dao.TopArticleDao
import uz.suhrob.domain.mapper.toDomainArticle
import uz.suhrob.domain.model.Article
import uz.suhrob.domain.model.Category

class GetArticlesByCategory(
    private val dao: TopArticleDao,
) {
    operator fun invoke(category: Category): Flow<List<Article>> {
        return dao.getTopArticlesByCategory(category.name)
            .map { list ->
                list.map { entity ->
                    entity.toDomainArticle()
                }
            }
    }
}