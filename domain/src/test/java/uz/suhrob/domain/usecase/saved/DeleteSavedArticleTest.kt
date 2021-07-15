package uz.suhrob.domain.usecase.saved

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uz.suhrob.database.entity.SavedArticleEntity
import uz.suhrob.domain.FakeSavedArticleDao
import uz.suhrob.domain.getFakeList

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class DeleteSavedArticleTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dao = FakeSavedArticleDao()
    private val deleteSavedArticle = DeleteSavedArticle(dao)

    @Before
    fun setUp() {
        dao.refresh()
    }

    @Test
    fun delete() = runBlockingTest {
        val fakeList = SavedArticleEntity.getFakeList(5)
        val article = fakeList[0]
        dao.setList(fakeList)
        deleteSavedArticle(article.id)
        val list = dao.getAllArticles().first()
        assertThat(list).doesNotContain(article)
    }
}