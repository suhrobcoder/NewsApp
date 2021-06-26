package uz.suhrob.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String,
)