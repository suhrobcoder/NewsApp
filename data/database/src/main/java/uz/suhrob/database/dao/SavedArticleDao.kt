package uz.suhrob.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.suhrob.database.entity.SavedArticleEntity

@Dao
interface SavedArticleDao {
    @Insert
    suspend fun insert(article: SavedArticleEntity)

    @Query("DELETE FROM saved_article WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM saved_article")
    fun getAllArticles(): Flow<List<SavedArticleEntity>>
}