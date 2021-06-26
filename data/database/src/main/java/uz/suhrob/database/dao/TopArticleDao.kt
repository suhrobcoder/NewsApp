package uz.suhrob.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.suhrob.database.entity.TopArticleEntity

@Dao
interface TopArticleDao {
    @Insert
    suspend fun insert(articles: List<TopArticleEntity>)

    @Query("DELETE FROM top_article WHERE category = :category")
    suspend fun clearTopArticlesByCategory(category: String)

    @Query("SELECT * FROM top_article WHERE category = :category")
    fun getTopArticlesByCategory(category: String): Flow<List<TopArticleEntity>>
}