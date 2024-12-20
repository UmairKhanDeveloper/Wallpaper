package com.example.wallpaper

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsConditionScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Terms & Conditions",
                        color = Color.White,
                        style = TextStyle(
                            fontStyle = FontStyle.Italic,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0XFF191E31)
                )
            )
        },
        containerColor = Color(0XFF1C1F33)
    ) { paddingValues ->

        val policyText = """
        
2. Copyright aur Intellectual Property

Wallpaper images ka copyright app developer ya third-party creators ke paas hota hai.
Users ko sirf personal use ke liye in wallpapers ko download karne ki ijazat hoti hai. Commercial use allowed nahi hota.

3. Third-party Content

Agar app third-party se wallpapers ya ads include kare, to unka content ussi third-party ke terms and conditions ke mutabiq hota hai.
Developer third-party content ke liye zimmedar nahi hota.

4. Usage Restrictions

App ko unauthorized tarikon se modify ya reverse engineer karna mana hota hai.
App ko illegal ya unethical activities ke liye use karna prohibited hota hai.

5. Liability Limitation

Developer kisi bhi loss, damage ya data breach ke liye zimmedar nahi hota, jo app ke istemal ke dauran ho.

6. In-app Purchases aur Subscriptions

Kuch apps mein paid features ya subscriptions hoti hain, aur terms in transactions ko cover karti hain.
Refund policies bhi terms mein define ki jaati hain.

7. Updates aur Changes

Developer kisi bhi waqt terms aur app features ko change karne ka haq rakhta hai.
Users ko changes ke baare mein notify kiya jata hai.

8. Termination Clause

Agar user terms ka violation kare, to developer user ka access terminate kar sakta hai.

9. Jurisdiction

Terms ke disputes ke liye specific laws aur courts ka zikr hota hai.
Aap ko hamesha terms and conditions ka review karna chahiye, khaaskar agar app sensitive permissions mang rahi ho. Agar aap kisi specific app ke terms ke baare mein poochna chahte hain, to uska naam bata dein.
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
