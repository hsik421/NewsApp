package com.project.news.data

import com.project.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun loadItems(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = "aec49dc8469c48848e2ae7e55b7d5d06"
    ): NewsResponse
}