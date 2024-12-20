package com.example.wallpaper

import android.annotation.SuppressLint
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PrivacyPolicyScreen(navController: NavHostController) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Privacy Policy",
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
       
        1. Information We Collect
        
        We may collect the following types of information:
        
        Personal Information:
        - Name, email address, or other contact details if you create an account or contact us for support.

        Device Information:
        - Device model, operating system, app version, unique device identifiers, and network information.

        Usage Data:
        - Information about how you interact with the App, including features used, wallpapers downloaded, and preferences set.

        Media Access:
        - Access to your device’s gallery or storage to enable you to set wallpapers, if granted by you.

        2. How We Use Your Information
        
        The collected information is used for:
        - Providing, maintaining, and improving the App.
        - Personalizing your experience (e.g., recommending wallpapers based on your preferences).
        - Addressing technical issues or customer support inquiries.
        - Serving advertisements (if applicable) via third-party ad providers.
        - Ensuring security and preventing fraud.

        3. Sharing Your Information
        
        We may share your information with:
        - Service Providers: Third-party vendors assisting in analytics, advertising, or app functionality.
        - Compliance and Legal Requirements: If required by law, to protect our rights, or to address fraudulent activities.

        4. Permissions
        
        The App may request the following permissions:
        - Storage: To save and set wallpapers on your device.
        - Internet Access: To download wallpapers and load in-app content.
        - Notifications: To send updates about new wallpapers, features, or promotions (optional).
        You can manage permissions through your device’s settings at any time.

        5. Security
        
        We implement industry-standard measures to protect your information. However, no method of transmission or storage is 100% secure. While we strive to protect your data, we cannot guarantee absolute security.

        6. Third-Party Services
        
        Our App may contain links to third-party websites or use third-party services (e.g., ad networks). We are not responsible for the privacy practices of these third parties. We encourage you to review their privacy policies.

        7. Children’s Privacy
        
        The App is not intended for children under the age of 13. We do not knowingly collect personal information from children. If we become aware of such data, we will take steps to delete it.

        8. Your Rights
        
        Depending on your location, you may have the following rights:
        - Access or request deletion of your personal data.
        - Opt-out of data collection for targeted advertising.
        - Withdraw consent for permissions granted.
        To exercise these rights, contact us at [Insert Contact Email].

        9. Changes to This Privacy Policy
        
        We may update this Privacy Policy from time to time. Any changes will be reflected in the “Effective Date” above. Continued use of the App after updates constitutes acceptance of the revised policy.

        10. Contact Us
        
        If you have any questions about this Privacy Policy, please contact us at:
        - Email: [Insert Contact Email]
        - Address: [Insert Physical Address, if applicable]
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