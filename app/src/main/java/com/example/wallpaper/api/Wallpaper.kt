package com.example.wallpaper.api

interface Wallpaper {
    suspend fun getallWallpaper():HomeWallpaper
    suspend fun searchWallPaper(query: String):HomeWallpaper
   suspend fun videowallpaper():videoswallpaper
}