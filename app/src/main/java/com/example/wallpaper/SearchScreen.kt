package com.example.wallpaper

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.wallpaper.api.HomeWallpaper
import com.example.wallpaper.api.MainViewModel
import com.example.wallpaper.api.Photo
import com.example.wallpaper.api.Reprository
import com.example.wallpaper.api.ResultState
import com.example.wallpaper.db.FavoriteDataBase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    val context = LocalContext.current
    val favoriteDataBase = remember { FavoriteDataBase.getDataBase(context) }
    val reprository = remember { Reprository(favoriteDataBase) }
    val viewModel = remember { MainViewModel(reprository) }
    var isloading by remember { mutableStateOf(false) }
    var SearchWallpaper by remember { mutableStateOf<HomeWallpaper?>(null) }
    var Scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.SearchWallpaper
    }


    val state by viewModel.SearchWallpaper.collectAsState()
    var searchBar by remember {
        mutableStateOf("")
    }

    when (state) {
        is ResultState.Error -> {
            isloading = false
            val error = (state as ResultState.Error).error
            Text(text = "$error")
        }

        ResultState.Loading -> {
            if (isloading){
            isloading = true
            }
        }

        is ResultState.Succses -> {
            isloading = false
            val succses = (state as ResultState.Succses).response
            SearchWallpaper = succses
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF14182b))
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(0XFF14182b)
                )
        ) {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0XFF14182b)),
                colors = SearchBarDefaults.colors(
                    containerColor = Color(0XFF14182b),
                    dividerColor = Color.White,
                    inputFieldColors = TextFieldDefaults.textFieldColors(Color.White)
                ),
                query = searchBar,
                onQueryChange = {
                    searchBar = it
                    isloading=true
                },
                onSearch = {
                    isloading=true
                    if (searchBar.isNotBlank()) {
                        Scope.launch {
                            viewModel.SearchWallpaper(searchBar.trim())
                        }
                    }
                },
                placeholder = {
                    Text(text = "Search Wallpaper", color = Color.White)
                },
                trailingIcon = {

                    if (searchBar >= 0.toString()) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier.clickable { searchBar = "" },
                            tint = Color.White
                        )
                    }

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                active = true,
                onActiveChange = {

                }) {
                if (isloading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color.White)
                    }
                } else {
                    SearchWallpaper?.photos?.let { photos ->
                        LazyColumn {
                            items(photos) { photo ->
                                SearchItem(photo = photo,navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SearchItem(photo: Photo,navController: NavHostController) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(1f)

            .clickable {
                navController.navigate(
                    Screens.DetailsScreen.route + "/${Uri.encode(photo.src.large)}"
                )
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = photo.src.landscape,
                contentDescription = "Wallpaper Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.TopEnd
            ) {

            }
        }
    }


}