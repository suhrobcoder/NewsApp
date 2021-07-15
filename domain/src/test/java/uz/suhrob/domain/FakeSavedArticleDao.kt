package uz.suhrob.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.suhrob.database.dao.SavedArticleDao
import uz.suhrob.database.entity.SavedArticleEntity
import kotlin.random.Random

class FakeSavedArticleDao : SavedArticleDao {
    private val articles = arrayListOf<SavedArticleEntity>()

    fun refresh() {
        articles.clear()
    }

    fun setList(list: List<SavedArticleEntity>) {
        articles.clear()
        articles.addAll(list)
    }

    override suspend fun insert(article: SavedArticleEntity) {
        articles.add(article)
    }

    override suspend fun delete(id: Int) {
        articles.removeIf { it.id == id }
    }

    override fun getAllArticles(): Flow<List<SavedArticleEntity>> {
        return flow {
            emit(articles)
        }
    }
}

fun SavedArticleEntity.Companion.getFake(): SavedArticleEntity {
    val fakeId = Random.nextInt()
    return SavedArticleEntity(
        source = "source $fakeId",
        author = "author $fakeId",
        title = "title $fakeId",
        description = "description $fakeId",
        url = "url $fakeId",
        imageUrl = "imageUrl $fakeId",
        publishedAt = "publishedAt $fakeId",
        content = "content $fakeId",
    ).apply { id = fakeId }
}

@ExperimentalStdlibApi
fun SavedArticleEntity.Companion.getFakeList(size: Int): List<SavedArticleEntity> {
    return (1..size).toList().map { SavedArticleEntity.getFake() }
}