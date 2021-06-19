package uz.suhrob.domain.model

data class Category(
    val title: String,
    val name: String,
)

val categories = listOf(
    Category(title = "All", name = ""),
    Category(title = "Business", name = "business"),
    Category(title = "Entertainment", name = "entertainment"),
    Category(title = "General", name = "general"),
    Category(title = "Health", name = "health"),
    Category(title = "Science", name = "science"),
    Category(title = "Sports", name = "sports"),
    Category(title = "Technology", name = "technology"),
)
