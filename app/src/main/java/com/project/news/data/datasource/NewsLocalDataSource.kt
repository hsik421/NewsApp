package com.project.news.data.datasource

import com.project.news.data.NewsDao
import com.project.news.data.model.News
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDao : NewsDao){
    suspend fun loadItems() : List<News> = newsDao.loadItems()
    suspend fun insertItems(items: List<News>){
        newsDao.insertItems(items)
    }
    suspend fun getVisitedItems(items : List<String>) = newsDao.getVisitedItems(items)
}