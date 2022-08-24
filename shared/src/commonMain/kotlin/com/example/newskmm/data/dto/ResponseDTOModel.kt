package com.example.newskmm.data.dto

import com.example.newskmm.usecase.ResponseModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTOModel(
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?,
    @SerialName("articles")
    val articles: ArrayList<Articles>?
) {
    @Serializable
    data class Articles(
        @SerialName("source")
        val source: Source?,
        @SerialName("author")
        val author: String?,
        @SerialName("title")
        val title: String?,
        @SerialName("description")
        val description: String?,
        @SerialName("url")
        val url: String?,
        @SerialName("urlToImage")
        val urlToImage: String?,
        @SerialName("publishedAt")
        val publishedAt: String?,
        @SerialName("content")
        val content: String?
    ) {
        @Serializable
        data class Source(
            @SerialName("id")
            val id: String?,
            @SerialName("name")
            val name: String?
        )
    }
}

fun ResponseDTOModel.toResponseModel(): ResponseModel {
    return ResponseModel(
        status = status,
        totalResults = totalResults,
        articles = ArrayList<ResponseModel.Articles>().apply {
            articles?.forEach {
                val source = ResponseModel.Articles.Source(it.source?.id, it.source?.name)
                add(
                    ResponseModel.Articles(
                        source = source,
                        author = it.author,
                        title = it.title,
                        description = it.description,
                        url = it.url,
                        urlToImage = it.urlToImage,
                        publishedAt = it.publishedAt,
                        content = it.content
                    )
                )
            }
        }
    )
}
