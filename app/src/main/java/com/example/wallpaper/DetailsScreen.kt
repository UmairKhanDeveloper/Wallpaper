package com.example.wallpaper

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.wallpaper.api.MainViewModel
import com.example.wallpaper.api.Reprository
import com.example.wallpaper.db.Favorite
import com.example.wallpaper.db.FavoriteDataBase

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

@SuppressLint("MissingPermission")
@Composable
fun DetailsScreen(navController: NavHostController, image: String?) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val DataBase = remember { FavoriteDataBase.getDataBase(context) }
    val reprository = remember { Reprository(DataBase) }
    val viewModel = remember { MainViewModel(reprository) }
    val wallpaperdata by viewModel.allWallpaperS.observeAsState()
    var isFavorited by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
        ) {
            AsyncImage(
                model = image.toString(),
                contentDescription = "Wallpaper Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .align(Alignment.TopCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { navController.navigate(Screens.HomeScreen.route) })
                Icon(imageVector = Icons.Default.Info,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { })

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 85.dp)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite Icon",
                    tint = if (isFavorited) Color.Red else Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            isFavorited = !isFavorited
                            if (isFavorited) {
                                viewModel.Insert(
                                    Favorite(
                                        id = null,
                                        image.toString(),
                                        " photo.src.large"
                                    )
                                )
                            } else {
                                viewModel.Delete(
                                    Favorite(
                                        id = null,
                                        image.toString(),
                                        "photo.src.large"
                                    )
                                )
                            }
                        }
                )
                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = "Image",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            scope.launch {
                                try {
                                    withContext(Dispatchers.IO) {
                                        val url = URL(image)
                                        val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                                        val wallpaperManager = WallpaperManager.getInstance(context)
                                        wallpaperManager.setBitmap(bitmap)
                                    }
                                    Toast.makeText(context, "Wallpaper Set Successfully", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    Toast.makeText(context, "Failed to Set Wallpaper", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                )
                Icon(
                    imageVector = Icons.Default.Download,
                    contentDescription = "Download",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { }
                )
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color.White,
                    modifier = Modifier
                        .size(32.dp)
                        .clickable { }
                )
            }

        }
    }

}




