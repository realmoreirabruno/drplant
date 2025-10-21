package com.example.doctorplant.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctorplant.R

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF8F8F8))
            .padding(bottom = 16.dp)
    ) {
        // 🌿 Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2E7D32), // Dark Green
                            Color(0xFF4CAF50)  // Light Green
                        )
                    ),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                )
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_leaf),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Dr. Plant",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Plant Health Expert",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Box {
//                            Icon(
//                                imageVector = Icons.Default.Notifications,
//                                contentDescription = "Notifications",
//                                tint = Color.White,
//                                modifier = Modifier.size(24.dp)
//                            )
//                            Box(
//                                modifier = Modifier
//                                    .size(8.dp)
//                                    .background(Color.Red, CircleShape)
//                                    .align(Alignment.TopEnd)
//                            )
//                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            tint = Color.White,
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                        )
                    }
                }

                // Search field
//                OutlinedTextField(
//                    value = "",
//                    onValueChange = {},
//                    placeholder = { Text("Search plant diseases...") },
//                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedContainerColor = Color.White.copy(alpha = 0.2f),
//                        unfocusedContainerColor = Color.White.copy(alpha = 0.2f),
//                        focusedBorderColor = Color.Transparent,
//                        unfocusedBorderColor = Color.Transparent,
//                        cursorColor = Color.White
//                    ),
//                    shape = RoundedCornerShape(8.dp),
//                    modifier = Modifier.fillMaxWidth()
//                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 👋 Welcome Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = "Welcome!",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Ready to diagnose your plants?",
                            color = Color.Gray,
                            fontSize = 13.sp
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.ic_leaf),
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatItem("24", "Scans Today", Color(0xFF4CAF50))
                    StatItem("156", "Total Scans", Color(0xFF1565C0))
                    StatItem("89%", "Accuracy", Color(0xFFD32F2F))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 📷 Scan section
        Text(
            text = "Scan Your Plant",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Take a photo or upload an image to identify diseases",
            color = Color.Gray,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Upload area
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .border(
                    width = 2.dp,
                    color = Color(0xFF81C784),
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE8F5E9).copy(alpha = 0.4f)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Capture",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Tap to capture", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                Text(
                    "or upload from gallery",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Take Photo", color = Color.White)
            }

            OutlinedButton(
                onClick = { /* TODO */ },
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFF81C784)),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color(0xFF2E7D32))
                Spacer(modifier = Modifier.width(8.dp))
                Text("From Gallery", color = Color(0xFF2E7D32))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 💡 Tips section
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = Color(0xFF1565C0)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Quick Tips for Better Results",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1565C0)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                TipItem("Ensure good lighting")
                TipItem("Focus on affected areas")
                TipItem("Include leaves and stems")
            }
        }
    }
}

@Composable
private fun StatItem(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, color = color, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(text = label, color = Color.Gray, fontSize = 13.sp)
    }
}

@Composable
private fun TipItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(Color(0xFF1565C0), CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color(0xFF1565C0), fontSize = 13.sp)
    }
}

@Preview(showSystemUi = true, showBackground = true, name = "Home Screen Preview")
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(navController = rememberNavController())
    }
}
