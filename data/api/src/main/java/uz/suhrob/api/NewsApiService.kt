package uz.suhrob.api

import retrofit2.http.GET
import retrofit2.http.Query
import uz.suhrob.api.response.ApiResponse

interface NewsApiService {
    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): ApiResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("language") language: String = "en",
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): ApiResponse
}