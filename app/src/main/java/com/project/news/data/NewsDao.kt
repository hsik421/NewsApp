package com.project.news.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.news.data.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM ${LocalConstants.NEWS_TABLE_NAME}")
    suspend fun loadItems() : List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<News>)

    @Query("SELECT * FROM ${LocalConstants.NEWS_TABLE_NAME} WHERE publishedAt IN (:items) and isVisited = 1")
    suspend fun getVisitedItems(items : List<String>) : List<News>
}