package com.example.wallpaper.api

import com.example.wallpaper.api.Constant.APIKEY
import com.example.wallpaper.api.Constant.BASE_URL
import com.example.wallpaper.api.Constant.TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object WallpaperclientApi {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    prettyPrint = true

                }
            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }

            }
        }

        install(HttpTimeout) {
            socketTimeoutMillis = TIMEOUT
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
        }
        defaultRequest {
            headers {
                header("Authorization", APIKEY)

            }
        }
    }

    suspend fun getAllWallpaper(): HomeWallpaper {
        return client.get(BASE_URL + "v1/search?query=nature&per_page=100").body()
    }

    suspend fun searchWallPaper(query: String):HomeWallpaper{
        return client.get(BASE_URL + "v1/search?query=$query&per_page=100").body()
    }
    suspend fun  videosWallpaer(): videoswallpaper {
        return client.get("https://api.pexels.com/videos/search?query=nature&per_page=100").body()
    }
}