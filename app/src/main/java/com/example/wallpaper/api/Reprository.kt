package com.example.wallpaper.api

import androidx.lifecycle.LiveData
import com.example.wallpaper.db.Favorite
import com.example.wallpaper.db.FavoriteDataBase

class Reprository(private val favoriteDataBase: FavoriteDataBase) :Wallpaper{
    override suspend fun getallWallpaper(): HomeWallpaper {
        return WallpaperclientApi.getAllWallpaper()
    }

    override suspend fun searchWallPaper(query: String): HomeWallpaper {
        return WallpaperclientApi.searchWallPaper(query)
    }

    fun getFavData(): LiveData<List<Favorite>> {
        return favoriteDataBase.getDao().getAllNotes()
    }

    suspend fun Insert(favorite: Favorite) {
        favoriteDataBase.getDao().Insert(favorite)
    }
    suspend fun Delete(favorite: Favorite){
        favoriteDataBase.getDao().Delete(favorite)
    }
}