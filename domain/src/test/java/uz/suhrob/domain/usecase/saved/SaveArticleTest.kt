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
import uz.suhrob.domain.getFake
import uz.suhrob.domain.getFakeList
import uz.suhrob.domain.mapper.toDomainModel

@ExperimentalCoroutinesApi
@ExperimentalStdlibApi
class SaveArticleTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dao = FakeSavedArticleDao()
    private val saveArticle = SaveArticle(dao)

    @Before
    fun setUp() {
        dao.refresh()
    }

    @Test
    fun save() = runBlockingTest {
        val fake = SavedArticleEntity.getFake()
        saveArticle(fake.toDomainModel())
        val list = dao.getAllArticles().first()
        assertThat(list).contains(fake)
    }
}