package uz.suhrob.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.suhrob.api.model.ArticleDto

@Serializable
data class ApiResponse(
    @SerialName("status") val status: String,
    @SerialName("totalResults") val totalResults: Int,
    @SerialName("articles") val articles: List<ArticleDto>,
)
