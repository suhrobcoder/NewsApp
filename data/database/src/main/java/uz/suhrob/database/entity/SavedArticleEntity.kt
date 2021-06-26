package uz.suhrob.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_article")
data class SavedArticleEntity(
    @ColumnInfo(name = "source") val source: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    @ColumnInfo(name = "content") val content: String,
) {
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0
}