package com.example.wallpaper

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.wallpaper.api.HomeWallpaper
import com.example.wallpaper.api.MainViewModel
import com.example.wallpaper.api.Photo
import com.example.wallpaper.api.Reprository
import com.example.wallpaper.api.ResultState
import com.example.wallpaper.db.Favorite
import com.example.wallpaper.db.FavoriteDataBase

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val Context = LocalContext.current
    val database = remember { FavoriteDataBase.getDataBase(Context) }
    val repository = remember { Reprository(database) }
    val viewModel = remember { MainViewModel(repository) }
    var isLoading by remember { mutableStateOf(false) }
    var wallpaperData by remember { mutableStateOf<HomeWallpaper?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getallWallpaper()
    }

    val state by viewModel.allWallpaper.collectAsState()

    when (state) {
        is ResultState.Error -> {
            isLoading = false
            val error = (state as ResultState.Error).error
            Text(text = "$error")
        }

        ResultState.Loading -> {
            isLoading = true
        }

        is ResultState.Succses -> {
            isLoading = false
            wallpaperData = (state as ResultState.Succses).response
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "New Wallpapers",
                        color = Color.White,
                        style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    Color(0XFF191E31)
                ),
                modifier = Modifier.shadow(elevation = 8.dp)
            )
        }
    ) {

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0XFF191E31)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                    trackColor = Color.White,
                )
            }
        }
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = it.calculateTopPadding(), bottom = 80.dp)
                .background(color = Color(0XFF191E31)),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),

            ) {
            wallpaperData?.photos?.let { photos ->
                items(photos) { photo ->
                    WallpaperItem(photo, viewModel, navController)
                }
            }
        }
    }
}


@Composable
fun WallpaperItem(photo: Photo, viewModel: MainViewModel, navController: NavHostController) {
    var isFavorited by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
            .clickable {
                navController.navigate(
                    Screens.DetailsScreen.route + "/${Uri.encode(photo.src.large)}"
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = photo.src.large,
                contentDescription = "Wallpaper Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            )

            Icon(
                imageVector = if (isFavorited) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = if (isFavorited) Color.Red else Color.White,
                modifier = Modifier
                    .size(35.dp)
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        isFavorited = !isFavorited
                        if (isFavorited) {
                            viewModel.Insert(
                                Favorite(
                                    id = null,
                                     photo.src.large,
                                   photo.src.large
                                )
                            )
                        } else {
                            viewModel.Delete(
                                Favorite(
                                    id = null,
                                    photo.src.large,
                                     photo.src.large
                                )
                            )
                        }
                    }
            )
        }
    }
}
