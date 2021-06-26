package uz.suhrob.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uz.suhrob.database.ArticleDatabase
import uz.suhrob.database.entity.SavedArticleEntity

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SavedArticleDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ArticleDatabase
    private lateinit var dao: SavedArticleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ArticleDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getSavedArticleDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    private fun getFakeArticle(title: String = "Title") = SavedArticleEntity(
        "BBC",
        "BBC",
        title,
        "Desc",
        "url",
        "image_url",
        "publish",
        "content"
    )

    @Test
    fun insertArticleTest() = runBlockingTest {
        val article = getFakeArticle()
        dao.insert(article)
        dao.insert(article)
        val insertedArticles = dao.getAllArticles().first()
        assertThat(insertedArticles).contains(article)
    }

    @Test
    fun deleteArticleTest() = runBlockingTest {
        val article = getFakeArticle()
        dao.insert(article)
        val articles = dao.getAllArticles().first()
        dao.delete(articles[0].id)
        val articles1 = dao.getAllArticles().first()
        assertThat(articles1).doesNotContain(articles[0])
    }
}