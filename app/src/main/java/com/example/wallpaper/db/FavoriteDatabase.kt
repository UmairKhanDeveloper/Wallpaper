package com.example.wallpaper.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun getDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDataBase? = null

        fun getDataBase(context: Context): FavoriteDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, FavoriteDataBase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
                instance
            }

        }

    }
}