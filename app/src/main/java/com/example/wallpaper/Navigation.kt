package com.example.wallpaper

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(Screens.FavoriteScreen.route){
            FavoriteScreen(navController)
        }
        composable(Screens.SearchScreen.route){
            SearchScreen(navController)
        }
        composable(Screens.SettingScreen.route){
            SettingScreen(navController)
        }
        composable(Screens.PrivacyPolicyScreen.route){
            PrivacyPolicyScreen(navController)
        }
        composable(Screens.FeedbackBugsScreeen.route){
          FeedbackBugsScreen(navController)
        }
        composable(Screens.TermsConditionScreen.route){
            TermsConditionScreen(navController)
        }

        composable(
            Screens.DetailsScreen.route + "/{src}",
            arguments = listOf(
                navArgument("src") {
                    type = NavType.StringType
                },

                )
        ) {
            val image = it.arguments?.getString("src")
            DetailsScreen(navController, image)
        }
    }



    }


sealed class Screens(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object HomeScreen : Screens(
        "HomeScreen",
        "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    object FavoriteScreen : Screens(
        "FavoriteScreen",
        "Favorites",
        selectedIcon = Icons.Filled.Favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder
    )

    object SearchScreen : Screens(
        "SearchScreen",
        "SearchScreen",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
    )
    object SettingScreen : Screens(
        "SettingScreen",
        "SettingScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object PrivacyPolicyScreen : Screens(
        "PrivacyPolicyScreen",
        "PrivacyPolicyScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object FeedbackBugsScreeen : Screens(
        "FeedbackBugsScreeen",
        "FeedbackBugsScreeen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object TermsConditionScreen : Screens(
        "TermsConditionScreen",
        "TermsConditionScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object DetailsScreen : Screens(
        "DetailsScreen",
        "DetailsScreen",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )
    object VideoswallpaperScreen : Screens(
        "VideoswallpaperScreen",
        "VideoswallpaperScreen",
        selectedIcon = Icons.Filled.Wallpaper,
        unselectedIcon = Icons.Outlined.Wallpaper
    )
    object VideoWallpaperDetails: Screens(
        "VideoWallpaperDetails",
        "VideoWallpaperDetails",
        selectedIcon = Icons.Filled.Wallpaper,
        unselectedIcon = Icons.Outlined.Wallpaper
    )


}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        Screens.HomeScreen,
        Screens.FavoriteScreen,
        Screens.SearchScreen,
        Screens.SettingScreen,
    )
    NavigationBar (containerColor = Color(0XFF191E31)){
        val navStack by navController.currentBackStackEntryAsState()
        val currentRoute = navStack?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        navController.graph?.let {
                            it.route?.let { it1 -> popUpTo(it1) }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Image(
                        imageVector = if (currentRoute == screen.route) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.title,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                            if (currentRoute == screen.route) Color.Black else Color.White
                        )

                    )
                },colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.White,
                    indicatorColor = Color(0xFF90A4AE)
                )


            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavEntry() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) {
        Navigation(navController)
    }
}

