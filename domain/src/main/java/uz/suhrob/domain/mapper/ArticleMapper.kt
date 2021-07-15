package uz.suhrob.domain.mapper

import uz.suhrob.api.model.ArticleDto
import uz.suhrob.database.entity.SavedArticleEntity
import uz.suhrob.database.entity.TopArticleEntity
import uz.suhrob.domain.model.Article
import uz.suhrob.domain.model.Category

fun TopArticleEntity.toDomainArticle(): Article {
    return Article(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}

fun Article.toDbModel(category: Category): TopArticleEntity {
    return TopArticleEntity(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        content = this.content,
        category = category.name,
    )
}

fun SavedArticleEntity.toDomainModel(): Article {
    return Article(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}

fun Article.toDbModel(): SavedArticleEntity {
    return SavedArticleEntity(
        source = this.source,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}

fun ArticleDto.toDomainModel(): Article {
    return Article(
        source = this.source.name,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        imageUrl = this.imageUrl,
        publishedAt = this.publishedAt,
        content = this.content,
    )
}