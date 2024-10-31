package com.project.news.utils

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Failure<out T>(val data: T,val exception: Exception) : ApiResult<T>()
}