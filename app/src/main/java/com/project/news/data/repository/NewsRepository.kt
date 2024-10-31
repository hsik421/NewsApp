package com.project.news.data.repository

import com.project.news.data.datasource.NewsLocalDataSource
import com.project.news.data.datasource.NewsRemoteDataSource
import com.project.news.data.RemoteConstants
import com.project.news.utils.ApiResult
import com.project.news.data.model.News
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
) {
    suspend fun loadItems(): ApiResult<List<News>> {
        return try {
            if (remoteDataSource.loadItems().status == RemoteConstants.SUCCESS_CODE) {
                remoteDataSource.loadItems().articles.let {
                    getVisitedItems(it)
                    insertItems(it)
                    ApiResult.Success(it)
                }
            } else {
                ApiResult.Failure(localDataSource.loadItems(), Exception("Response status not ok"))
            }
        } catch (e: Exception) {
            ApiResult.Failure(localDataSource.loadItems(), e)
        }
    }

    suspend fun insertItems(items: List<News>) {
        localDataSource.insertItems(items)
    }

    private suspend fun getVisitedItems(items: List<News>) {
        localDataSource.getVisitedItems(items.map { it.publishedAt }).forEach { local ->
            items.find { local.publishedAt == it.publishedAt }?.isVisited = 1
        }
    }
}
