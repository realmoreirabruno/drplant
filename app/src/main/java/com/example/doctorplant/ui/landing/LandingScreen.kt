package com.example.doctorplant.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doctorplant.R
import com.example.doctorplant.ui.components.GenericButton
import com.example.doctorplant.ui.components.GenericOutlinedButton
import com.example.doctorplant.ui.theme.Dimmed
import com.example.doctorplant.ui.theme.GreenButton
import com.example.doctorplant.ui.theme.GreenHome
import com.example.doctorplant.ui.theme.GreenIcon

@Composable
fun LandingScreen(navController: NavController) {
    Box(
        modifier = Modifier.background(GreenHome)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .statusBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = GreenIcon,
                        shape = CircleShape
                    )
                    .size(80.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_leaf),
                    contentDescription = "App Icon",
                    tint = Color.White,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Dr. Plant",
                style = typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "AI Plant Disease Expert",
                style = typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Instantly diagnose plant disease with\n advanced AI technology. " +
                        "Keep your\n garden healthy and thriving",
                style = typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Dimmed
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_camera),
                        contentDescription = "Camera",
                        tint = Dimmed
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Instant\nScan",
                        style = typography.labelSmall,
                        color = Dimmed,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_brain),
                        contentDescription = "Brain",
                        tint = Dimmed
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AI\nPowered",
                        style = typography.labelSmall,
                        color = Dimmed,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Row {
                    Icon(
                        painter = painterResource(R.drawable.ic_analysis),
                        contentDescription = "Analysis",
                        tint = Dimmed
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Track\nHealth",
                        style = typography.labelSmall,
                        color = Dimmed,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            GenericButton(
                text = "Get started",
                contentColor = GreenButton,
                onClick = { navController.navigate("login")},
                buttonLarge = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            GenericOutlinedButton(
                text = "Learn more",
                onClick = {},
                buttonLarge = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}