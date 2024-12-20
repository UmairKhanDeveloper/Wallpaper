package com.example.wallpaper

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedbackBugsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Feedback & Bugs",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                }, navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF191E31))
            )
        },containerColor = Color(0XFF1C1F33)

    ) { paddingValues ->
        val policyText = """
            **Feedback Collection**
            - **User Feedback Forms**: Add an in-app feedback section for users to submit issues or suggestions.
            - **App Store Reviews**: Analyze reviews to understand what users appreciate and what needs improvement.
            - **In-App Surveys**: Present short surveys to gather user preferences and experiences.
            - **Social Media Engagement**: Actively engage with users through social media comments and messages.

            **Bugs Fixing Suggestions**
            - **Screen Resolution Issues**: Ensure wallpapers adapt to different devices using responsive layouts.
            - **App Crashes**: Use tools like Firebase Crashlytics to track and fix app crashes.
            - **Slow Loading**: Optimize images (e.g., use WebP format) to reduce loading times.
            - **Search Functionality**: Improve the search algorithm to help users find desired wallpapers easily.
            - **Permission Errors**: Request only essential permissions and explain their purpose clearly to users.

            **Improvement Areas**
            - **High-Quality Images**: Focus on providing high-resolution, clear wallpapers.
            - **Categorization**: Organize wallpapers into categories (e.g., Nature, Abstract, Anime, Minimalist).
            - **Personalization**: Allow users to save favorite wallpapers and get recommendations based on preferences.
            - **Regular Updates**: Update the app regularly with new wallpapers and features.
            - **Offline Mode**: Provide an offline mode for downloaded wallpapers.
        """.trimIndent()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top =10.dp, bottom = 85.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    item {
                        Text(
                            text = policyText,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 22.sp
                        )
                    }
                }
            }
        }
    }
}
