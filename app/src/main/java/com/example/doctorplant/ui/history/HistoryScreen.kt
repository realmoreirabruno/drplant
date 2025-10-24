package com.example.doctorplant.ui.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctorplant.R
import com.example.doctorplant.data.model.HistoryItem

@Composable
fun HistoryScreen(navController: NavController) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF2E7D32), Color(0xFF43A047))
    )

    val historyItems = listOf(
        HistoryItem(
            plantName = "Tomato Plant",
            status = "Diseased",
            disease = "Late Blight Disease",
            confidence = 94,
            advice = "Immediate treatment required",
            time = "2:34 PM",
            imageRes = R.drawable.ic_alert,
            color = Color(0xFFFFEBEE)
        ),
        HistoryItem(
            plantName = "Rose Bush",
            status = "Healthy",
            disease = "Plant is Healthy",
            confidence = 98,
            advice = "Continue current care routine",
            time = "11:45 AM",
            imageRes = R.drawable.ic_alert,
            color = Color(0xFFE8F5E9)
        ),
        HistoryItem(
            plantName = "Cucumber Plant",
            status = "Warning",
            disease = "Powdery Mildew",
            confidence = 87,
            advice = "Early stage - treatable",
            time = "9:22 AM",
            imageRes = R.drawable.ic_alert,
            color = Color(0xFFFFFDE7)
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .verticalScroll(rememberScrollState())
    ) {
//        OutlinedTextField(
//            value = "",
//            onValueChange = {},
//            modifier = Modifier
//                .padding(horizontal = 16.dp, vertical = 8.dp)
//                .fillMaxWidth(),
//            placeholder = { Text("Search your plant diagnoses...") },
//            leadingIcon = {
//                Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
//            },
//            shape = RoundedCornerShape(12.dp),
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0xFF2E7D32),
//                unfocusedContainerColor = Color(0xFFE0E0E0)
//            )
//        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilterButton("All Time", true)
            FilterButton("Today", false)
            FilterButton("This Week", false)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(vertical = 24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem("247", "Total Scans", Color.White)
                StatItem("38", "Diseases Found", Color.White)
                StatItem("85%", "Accuracy Rate", Color.White)
            }
        }

        Spacer(Modifier.height(16.dp))
        Text(
            "Recent Activity",
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 18.sp
        )

        Column(Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            historyItems.forEach { item ->
                HistoryCard(item)
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun HistoryCard(item: HistoryItem) {
    val (dotColor, statusColor) = when (item.status) {
        "Diseased" -> Color.Red to Color(0xFFFFEBEE)
        "Healthy" -> Color(0xFF4CAF50) to Color(0xFFE8F5E9)
        "Warning" -> Color(0xFFFFC107) to Color(0xFFFFFDE7)
        else -> Color.Gray to Color.LightGray
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row (verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.imagemsoja),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 80.dp, height = 140.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(Modifier.padding(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text =item.plantName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(dotColor, CircleShape)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(item.status, color = dotColor, fontSize = 12.sp)
                    }
                }

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(item.color, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ){
                        Text(
                            text = item.disease,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            "Confidence: ${item.confidence}%",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Text(
                            item.advice,
                            color = dotColor,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilterButton(text: String, selected: Boolean) {
    if (selected) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(Icons.Default.DateRange, null, tint = Color.White, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            Text(text, color = Color.White, fontSize = 13.sp)
        }
    } else {
        OutlinedButton(
            onClick = {},
            border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(Icons.Default.DateRange, null, tint = Color.Gray, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            Text(text, color = Color.Gray, fontSize = 13.sp)
        }
    }
}

@Composable
fun StatItem(value: String, label: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = color, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Text(label, color = color.copy(alpha = 0.8f), fontSize = 13.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(navController = rememberNavController())
}
