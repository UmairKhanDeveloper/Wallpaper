package com.example.wallpaper

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.wallpaper.api.MainViewModel
import com.example.wallpaper.api.Reprository
import com.example.wallpaper.db.Favorite
import com.example.wallpaper.db.FavoriteDataBase
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(navController: NavHostController) {
    val context = LocalContext.current
    val DataBase = remember { FavoriteDataBase.getDataBase(context) }
    val reprository = remember { Reprository(DataBase) }
    val viewModel = remember { MainViewModel(reprository) }
    val wallpaperdata by viewModel.allWallpaperS.observeAsState()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Favorite Wallpaper",
                    color = Color.White,
                    style = TextStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)
                )

            }, colors = TopAppBarDefaults.topAppBarColors(
                Color(0XFF191E31)
            )
        )
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding(), bottom = 80.dp)
                .background(color = Color(0XFF191E31))
                .verticalScroll(rememberScrollState())
        ) {
            wallpaperdata?.let { data ->
                data.forEach { favorite ->
                    FavoriteItem(
                        favorite = favorite,
                        viewModel = viewModel,navController
                    )
                }
            }
        }

    }

}

@Composable
fun FavoriteItem(favorite: Favorite, viewModel: MainViewModel,navController: NavHostController) {
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .aspectRatio(1f)

            .clickable {
                navController.navigate(
                    Screens.DetailsScreen.route + "/${Uri.encode(favorite.title)}"
                )
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = favorite.title,
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
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxHeight()
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(32.dp)
                            .padding(4.dp)
                            .clickable { scope.launch { viewModel.Delete(favorite) } }
                    )
                }
            }
        }
    }
}
