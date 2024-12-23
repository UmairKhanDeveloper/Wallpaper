package com.example.wallpaper.api

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val avg_color: String?=null,
    val duration: Int,
    val full_res: String?=null,
    val height: Int,
    val id: Int,
    val image: String,
    val tags: List<String>,
    val url: String,
    val user: User,
    val video_files: List<VideoFile>,
    val video_pictures: List<VideoPicture>,
    val width: Int
)