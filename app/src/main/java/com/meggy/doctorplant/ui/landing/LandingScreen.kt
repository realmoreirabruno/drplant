package com.meggy.doctorplant.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.meggy.doctorplant.R
import com.meggy.doctorplant.ui.components.GenericButton
import com.meggy.doctorplant.ui.components.GenericOutlinedButton
import com.meggy.doctorplant.ui.theme.BeautifulGreen
import com.meggy.doctorplant.ui.theme.Dimmed
import com.meggy.doctorplant.ui.theme.Typography

@Composable
fun LandingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BeautifulGreen)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = BeautifulGreen,
                        shape = CircleShape
                    )
                    .size(80.dp)
                    .border(
                        width = 0.5.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
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
                style = Typography.titleLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Especialista em diagnóstico de plantas",
                style = Typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Diagnostique doenças de plantas instantaneamente com tecnologia avançada de IA. " +
                        "Mantenha seu jardim saudável e próspero.",
                style = Typography.bodyMedium,
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
                        text = "Scan\nRápido",
                        style = Typography.labelSmall,
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
                        text = "Feito com\nIA",
                        style = Typography.labelSmall,
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
                        text = "Diagnóstico\nPersonalizado",
                        style = Typography.labelSmall,
                        color = Dimmed,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            GenericButton(
                text = "Comece aqui",
                contentColor = BeautifulGreen,
                onClick = { navController.navigate("home")},
                buttonLarge = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            GenericOutlinedButton(
                text = "Saiba mais sobre o projeto",
                onClick = { navController.navigate("learn_more") },
                buttonLarge = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Preview(showSystemUi = true, showBackground = true, name = "Landing Screen Preview")
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        LandingScreen(navController = rememberNavController())
    }
}
