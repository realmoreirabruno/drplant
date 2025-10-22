package com.example.doctorplant.ui.diagnosis

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DiagnosisScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Diagnosis Results",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0E0E0))
        ) {
            Image(
                painter = painterResource(android.R.drawable.ic_menu_gallery),
                contentDescription = "Analyzed Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Text(
                text = "Analyzed Image",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(Color(0xAA000000))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
            IconButton(
                onClick = { /* Zoom */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Zoom", tint = Color(0xFF2E7D32))
            }
        }

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            StatusChip("Identified", Color(0xFF4CAF50))
            StatusChip("99.2% Match", Color(0xFF2196F3))
            StatusChip("2.3s Scan", Color(0xFF9C27B0))
        }

        Spacer(Modifier.height(20.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8F8)),
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {

                // Header
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(android.R.drawable.presence_busy),
                        contentDescription = null,
                        tint = Color(0xFFD32F2F),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Bacterial Leaf Spot", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(
                            "Xanthomonas campestris",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFFFCDD2), RoundedCornerShape(20.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text("High Severity", color = Color(0xFFD32F2F), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text("Confidence Level", color = Color.Gray, fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("99.2%", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                    Spacer(Modifier.width(8.dp))
                    LinearProgressIndicator(
                        progress = { 0.992f },
                        modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                        color = Color(0xFF4CAF50),
                        trackColor = Color(0xFFE0E0E0),
                        strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                    )
                }

                Spacer(Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RiskInfo(
                        title = "Spread Risk",
                        value = "High",
                        color = Color(0xFFFF5722),
                        icon = Icons.Default.ShoppingCart
                    )
                    RiskInfo(
                        title = "Plant Health",
                        value = "Critical",
                        color = Color(0xFFD32F2F),
                        icon = Icons.Default.ShoppingCart
                    )
                }

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = { /* TODO: Navigate to treatment screen */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.White)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Symptoms, Treatment and Prevention",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun StatusChip(label: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(label, color = color, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun RiskInfo(title: String, value: String, color: Color, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        Icon(icon, contentDescription = null, tint = color)
        Spacer(Modifier.height(6.dp))
        Text(title, color = Color.Gray, fontSize = 13.sp)
        Text(value, color = color, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun DiagnosisScreenPreview() {
    DiagnosisScreen(navController = rememberNavController())
}
