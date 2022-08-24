package com.example.newskmm.data.client

import com.example.newskmm.config.ApiConfig
import com.example.newskmm.data.dto.ResponseDTOModel
import io.ktor.client.call.*
import io.ktor.client.request.*

interface NewsApi {

    suspend fun everything(qInTitle: String): ResponseDTOModel

    suspend fun topHeadlines(category: String, country: String): ResponseDTOModel

    class Base(private val client: Client) : NewsApi {

        override suspend fun everything(qInTitle: String): ResponseDTOModel {
            return client.getClient().get("everything?${ApiConfig.qInTitle}=$qInTitle").body()
        }

        override suspend fun topHeadlines(category: String, country: String): ResponseDTOModel {
            return client.getClient().get("top-headlines?country=$country&category=$category")
                .body()
        }

    }

}