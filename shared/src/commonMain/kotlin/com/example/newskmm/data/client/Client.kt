package com.example.newskmm.data.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface Client {

    fun getClient(): HttpClient

    class Base() : Client {

        override fun getClient(): HttpClient {
            val client = HttpClient(CIO) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
                install(ContentNegotiation){
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                    })
                }
                defaultRequest {
                    url("https://newsapi.org/v2/")
                    header("Authorization", "5769b78a970e4a4e83274770741b02c0")
                }
            }
            return client
        }
    }
}