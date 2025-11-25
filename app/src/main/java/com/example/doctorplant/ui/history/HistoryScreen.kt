package com.example.doctorplant.ui.history

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.doctorplant.R
import com.example.doctorplant.data.model.DiagnosisHistory

@Composable
fun HistoryScreen(
    historyItems: List<DiagnosisHistory>,
    onItemClick: (DiagnosisHistory) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        if (historyItems.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum diagnóstico salvo ainda.", color = Color.Gray)
                }
            }
        } else {
            items(historyItems) { item ->
                HistoryCard(
                    item = item,
                    onClick = { onItemClick(item) }
                )
            }
        }
    }
}

//@Composable
//fun HistoryScreen(
//    historyItems: List<DiagnosisHistory>,
//    onItemClick: (DiagnosisHistory) -> Unit
//) {
//    val gradient = Brush.horizontalGradient(
//        colors = listOf(Color(0xFF2E7D32), Color(0xFF43A047))
//    )
//
//    val historyItems = listOf(
//        HistoryItem(
//            plantName = "Tomato Plant",
//            status = true,
//            disease = "Late Blight Disease",
//            confidence = 94,
//            advice = "Immediate treatment required",
////            time = "2:34 PM",
//            imageRes = R.drawable.ic_alert,
//            color = Color(0xFFFFEBEE)
//        ),
//        HistoryItem(
//            plantName = "Rose Bush",
//            status = false,
//            disease = "Plant is Healthy",
//            confidence = 98,
//            advice = "Continue current care routine",
////            time = "11:45 AM",
//            imageRes = R.drawable.ic_alert,
//            color = Color(0xFFE8F5E9)
//        ),
//        HistoryItem(
//            plantName = "Cucumber Plant",
//            status = true,
//            disease = "Powdery Mildew",
//            confidence = 87,
//            advice = "Early stage - treatable",
////            time = "9:22 AM",
//            imageRes = R.drawable.ic_alert,
//            color = Color(0xFFFFFDE7)
//        )
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFF8F9FA))
//            .verticalScroll(rememberScrollState())
//    ) {
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
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp, vertical = 8.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            FilterButton("All Time", true)
//            FilterButton("Today", false)
//            FilterButton("This Week", false)
//        }
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(gradient)
//                .padding(vertical = 24.dp)
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                StatItem("247", "Total Scans", Color.White)
//                StatItem("38", "Diseases Found", Color.White)
//                StatItem("85%", "Accuracy Rate", Color.White)
//            }
//        }
//
//        Spacer(Modifier.height(16.dp))
//        Text(
//            "Atividade Recente",
//            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
//            fontWeight = FontWeight.Bold,
//            color = Color.Black,
//            fontSize = 18.sp
//        )
//
//        if (historyItems.isEmpty()) {
//            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text("Nenhum diagnóstico salvo ainda.", color = Color.Gray)
//            }
//        } else {
//            LazyColumn(
//                contentPadding = PaddingValues(16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                items(historyItems) { item ->
//                    HistoryCard(
//                        item = item,
//                        onClick = { onItemClick(item) }
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun HistoryCard(item: DiagnosisHistory, onClick: () -> Unit) {
    val isDiseased = item.diagnosisStatus != "Saudável"
    val (dotColor, bgColor) = if (isDiseased) {
        Color.Red to Color(0xFFFFEBEE)
    } else {
        Color(0xFF4CAF50) to Color(0xFFE8F5E9)
    }
    val advice = if (isDiseased) {
        "Necessário tratamento"
    } else {
        "Nenhum tratamento necessário"
    }

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .height(IntrinsicSize.Min)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = Uri.parse(item.imageUri),
                contentDescription = null,
                placeholder = painterResource(R.drawable.imagemsoja),
                modifier = Modifier
                    .width(90.dp)
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .padding(12.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.diseaseName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(dotColor, CircleShape)
                        )
                        Spacer(Modifier.width(4.dp))
                        if (isDiseased) {
                            Text("Diseased", color = dotColor, fontSize = 12.sp)
                        } else {
                            Text("Healthy", color = dotColor, fontSize = 12.sp)
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor, RoundedCornerShape(12.dp))
                        .padding(8.dp)
                ) {
                    Column {
                        Text(
                            text = item.diseaseName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Text(
                            "Nível de confiança: ${item.confidence}",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Text(
                            advice,
                            color = dotColor,
                            fontSize = 12.sp
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

@Preview
@Composable
fun HistoryScreenPreview() {
    val mockList = listOf(
        DiagnosisHistory(
            id = 1,
            imageUri = "",
            diseaseName = "Ferrugem",
            diagnosisStatus = "Doente",
            technicalId = "rust",
            description = "Teste",
            treatment = "Agua",
            symptoms = listOf("Manchas"),
            confidence = "99.29%"
        )
    )

    HistoryScreen(
        historyItems = mockList,
        onItemClick = {}
    )
}
