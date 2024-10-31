package com.project.news.data.datasource

import com.project.news.data.ApiService
import com.project.news.data.model.NewsResponse
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun loadItems(): NewsResponse = apiService.loadItems()
}