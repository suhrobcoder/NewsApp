package uz.suhrob.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.suhrob.database.ArticleDatabase
import uz.suhrob.database.entity.TopArticleEntity

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TopArticleDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArticleDatabase
    private lateinit var dao: TopArticleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getTopArticleDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    private fun getFakeArticle(title: String = "Title", category: String = "sport") =
        TopArticleEntity(
            "BBC",
            "BBC",
            title,
            "Desc",
            "url",
            "image_url",
            category,
            "publish",
            "content"
        )

    @Test
    fun insertTest() = runBlocking {
        val category = "sport"
        val fakeArticle = getFakeArticle(category = category)
        dao.insert(listOf(fakeArticle))
        val articles = dao.getTopArticlesByCategory(category).first()
        assertThat(articles).contains(fakeArticle)
    }

    @Test
    fun getTopArticlesByCategory_returnsRightCategory() = runBlocking {
        val category1 = "technology"
        val category2 = "business"
        val fakeArticle1 = getFakeArticle(category = category1)
        val fakeArticle2 = getFakeArticle(category = category2)
        dao.insert(listOf(fakeArticle1, fakeArticle2))
        val articles = dao.getTopArticlesByCategory(category1).first()
        assertThat(articles).contains(fakeArticle1)
    }

    @Test
    fun getTopArticlesByCategory_doesntReturnRightCategory() = runBlocking {
        val category1 = "technology"
        val category2 = "business"
        val fakeArticle1 = getFakeArticle(category = category1)
        val fakeArticle2 = getFakeArticle(category = category2)
        dao.insert(listOf(fakeArticle1, fakeArticle2))
        val articles = dao.getTopArticlesByCategory(category1).first()
        assertThat(articles).doesNotContain(fakeArticle2)
    }

    @Test
    fun clearTopArticlesByCategory_deletesByCategory() = runBlocking {
        val category1 = "technology"
        val fakeArticle1 = getFakeArticle(category = category1)
        val fakeArticle2 = getFakeArticle(category = category1)
        dao.insert(listOf(fakeArticle1, fakeArticle2))
        dao.clearTopArticlesByCategory(category1)
        val articles = dao.getTopArticlesByCategory(category1).first()
        assertThat(articles).isEmpty()
    }

    @Test
    fun clearTopArticlesByCategory_doesntDeleteOtherCategories() = runBlocking {
        val category1 = "technology"
        val category2 = "business"
        val fakeArticle1 = getFakeArticle(category = category1)
        val fakeArticle2 = getFakeArticle(category = category1)
        val fakeArticle3 = getFakeArticle(category = category2)
        dao.insert(listOf(fakeArticle1, fakeArticle2, fakeArticle3))
        dao.clearTopArticlesByCategory(category1)
        val articles = dao.getTopArticlesByCategory(category2).first()
        assertThat(articles).contains(fakeArticle3)
    }
}