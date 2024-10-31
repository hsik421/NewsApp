package com.project.news.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.news.data.LocalConstants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = LocalConstants.NEWS_TABLE_NAME)
data class News(
    @PrimaryKey
    val publishedAt: String,
    val urlToImage: String?,
    val url: String,
    val title: String,
    var isVisited : Int = 0
) : Parcelable

@Parcelize
data class NewsResponse(
    val status : String,
    val totalResults : Int,
    val articles : List<News>,
) : Parcelable
