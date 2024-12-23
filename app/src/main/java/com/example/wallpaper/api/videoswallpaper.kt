package com.example.wallpaper.api

import kotlinx.serialization.Serializable

@Serializable
data class videoswallpaper(
    val next_page: String?=null,
    val page: Int?=null,
    val per_page: Int?=null,
    val total_results: Int?=null,
    val url: String?=null,
    val videos: List<Video>?=null
)