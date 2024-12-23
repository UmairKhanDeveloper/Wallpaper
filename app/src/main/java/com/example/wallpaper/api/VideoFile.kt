package com.example.wallpaper

data class VideoFile(
    val file_type: String,
    val fps: Double,
    val height: Int,
    val id: Int,
    val link: String,
    val quality: String,
    val size: Int,
    val width: Int
)