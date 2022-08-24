package com.example.newskmm.android

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newskmm.data.client.Client
import com.example.newskmm.data.client.NewsApi
import com.example.newskmm.usecase.NewsApiUseCase
import com.example.newskmm.usecase.ResponseModel
import kotlinx.coroutines.*

class MainViewModel() : ViewModel() {

    private val _newsList = MutableLiveData<ArrayList<ResponseModel.Articles>>()
    val newsList: LiveData<ArrayList<ResponseModel.Articles>> = _newsList

    private val useCase = NewsApiUseCase(NewsApi.Base(Client.Base()))

    private val coroutineScope = SupervisorJob() + Dispatchers.Main
    private val viewModelScope = CoroutineScope(coroutineScope)

    fun loadNews(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) return
        viewModelScope.launch(Dispatchers.IO) {
            _newsList.postValue(useCase.topHeadlines("business").articles)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
        viewModelScope.cancel()
    }

}