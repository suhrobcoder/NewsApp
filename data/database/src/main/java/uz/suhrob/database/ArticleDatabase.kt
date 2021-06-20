package uz.suhrob.database

import androidx.room.Database
import uz.suhrob.database.dao.SavedArticleDao
import uz.suhrob.database.dao.TopArticleDao
import uz.suhrob.database.entity.SavedArticleEntity
import uz.suhrob.database.entity.TopArticleEntity

private const val DATABASE_VERSION = 1
const val DATABASE_NAME = "articles.db"

@Database(entities = [SavedArticleEntity::class, TopArticleEntity::class], version = DATABASE_VERSION)
abstract class ArticleDatabase {
    abstract fun getSavedArticleDao(): SavedArticleDao
    abstract fun getTopArticleDao(): TopArticleDao
}