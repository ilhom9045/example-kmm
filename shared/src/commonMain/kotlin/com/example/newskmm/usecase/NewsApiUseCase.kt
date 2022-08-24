package com.example.newskmm.usecase

import com.example.newskmm.config.ApiConfig
import com.example.newskmm.data.client.NewsApi
import com.example.newskmm.data.dto.toResponseModel

class NewsApiUseCase(private val newsApi: NewsApi) {

    suspend fun everything(qInTitle: String): ResponseModel {
        return newsApi.everything(qInTitle).toResponseModel()
    }

    suspend fun topHeadlines(
        category: String,
        country: String = ApiConfig.countryUS
    ): ResponseModel {
        return newsApi.topHeadlines(category, country).toResponseModel()
    }

}