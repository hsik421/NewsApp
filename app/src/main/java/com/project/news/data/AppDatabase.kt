package com.project.news.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.news.data.model.News

@Database(
    entities = [
        News::class
    ], version = LocalConstants.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDao
}