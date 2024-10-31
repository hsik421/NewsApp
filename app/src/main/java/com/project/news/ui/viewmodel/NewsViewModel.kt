package com.project.news.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.news.data.repository.NewsRepository
import com.project.news.utils.SingleLiveEvent
import com.project.news.utils.ApiResult
import com.project.news.data.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository): ViewModel() {

    private val _newItems = MutableLiveData<List<News>>(listOf())
    val newsItems get() = _newItems

    val startWebViewEvent = SingleLiveEvent<String>()
    val showProgress = SingleLiveEvent<Boolean>()
    val showAlertEvent = SingleLiveEvent<String>()
    init {
        loadItem()
    }

    private fun loadItem() {
        viewModelScope.launch {
            showProgress.value = true
            when(val result = newsRepository.loadItems()){
                is ApiResult.Success->{
                    _newItems.value = result.data
                    showAlertEvent.value = "Load Success"
                }
                is ApiResult.Failure->{
                    _newItems.value = result.data
                    showAlertEvent.value = result.exception.message
                }
            }
            showProgress.value = false
        }

    }

    fun startWebView(item : News){
        if(item.url == "https://removed.com") return
        viewModelScope.launch {
            item.isVisited = 1
            newsRepository.insertItems(listOf(item))
            startWebViewEvent.value = item.url
        }

    }
//    fun moreLoadItem() {
//        viewModelScope.launch {
//            val nextPage = newsItems.value?.size?:0
//            loadItem(nextPage)
//        }
//    }
}