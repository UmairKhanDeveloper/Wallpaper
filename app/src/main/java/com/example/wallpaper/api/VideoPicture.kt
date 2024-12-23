package com.example.wallpaper.api

import kotlinx.serialization.Serializable

@Serializable
data class VideoPicture(
    val id: Int,
    val nr: Int,
    val picture: String
)