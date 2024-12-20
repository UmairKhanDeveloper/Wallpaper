package com.example.wallpaper.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.wallpaper.db.Favorite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val reprository: Reprository) : ViewModel() {
    private val _allWallpaper = MutableStateFlow<ResultState<HomeWallpaper>>(ResultState.Loading)
    val allWallpaper: StateFlow<ResultState<HomeWallpaper>> = _allWallpaper.asStateFlow()

    private val _SearchWallpaper = MutableStateFlow<ResultState<HomeWallpaper>>(ResultState.Loading)
    val SearchWallpaper: StateFlow<ResultState<HomeWallpaper>> = _SearchWallpaper.asStateFlow()

    val allWallpaperS: LiveData<List<Favorite>> = reprository.getFavData()


    fun Insert(favorite: Favorite) {
        viewModelScope.launch {
            try {
                reprository.Insert(favorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun Delete(favorite: Favorite) {
        viewModelScope.launch {
            try {
                reprository.Delete(favorite)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    suspend fun getallWallpaper() {
        viewModelScope.launch {
            _allWallpaper.value = ResultState.Loading
            try {
                val response = reprository.getallWallpaper()
                _allWallpaper.value = ResultState.Succses(response)
            } catch (e: Exception) {
                _allWallpaper.value = ResultState.Error(e)

            }
        }
    }

    fun SearchWallpaper(query: String) {
        viewModelScope.launch {
            _SearchWallpaper.emit(ResultState.Loading)
            try {
                val response = reprository.searchWallPaper(query)
                _SearchWallpaper.emit(ResultState.Succses(response))
            } catch (e: Exception) {
                _SearchWallpaper.emit(ResultState.Error(e))
            }
        }
    }


}