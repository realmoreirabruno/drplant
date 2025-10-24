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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctorplant.R
import com.example.doctorplant.ui.theme.Black600
import com.example.doctorplant.ui.theme.DoctorPlantTheme

@Composable
fun DiagnosisScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        var name by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(
                    text = "Diagnosis name...",
                    color = Black600
                ) },
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(250.dp)
                .padding(horizontal = 12.dp)
                .align(Alignment.CenterHorizontally)
        )


        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0E0E0))
        ) {
            Image(
                painter = painterResource(R.drawable.imagemsoja),
                contentDescription = "Analyzed Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
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
        }

        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            StatusChip("Identified", Color(0xFF4CAF50))
            StatusChip("2.3s Scan", Color(0xFF9C27B0))
        }

        Spacer(Modifier.height(16.dp))

        Column(modifier = Modifier.padding(8.dp)) {
            Row {
                Column {
                    Text(
                        text = "Leaf Spot",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    Text(
                        text = "Xanthomonas campestris",
                        color = Color(0xFF757575),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(Modifier.weight(1f))

                Box(modifier = Modifier.padding(top = 8.dp)) {
                    StatusChip(
                        label = "High Severity",
                        Color(0xFFD32F2F),
                        horizontalPadding = 10.dp,
                        verticalPadding = 4.dp,
                        roundedLevel = 20.dp
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                AssistChip(
//                    onClick = {},
//                    label = { Text("Low Severity") },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.CheckCircle,
//                            contentDescription = null,
//                            tint = Color(0xFF4CAF50)
//                        )
//                    }
//                )
//            }

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

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(all = 12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "A fungal or bacterial infection causing circular spots on leaves, ranging from brown to black. While usually not fatal, it can weaken plants and reduce their aesthetic appeal.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_treatment),
                            contentDescription = null,
                            tint = Color(0xFF4CAF50)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Recommended Treatment",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    val treatments = listOf(
                        "Prune affected leaves and dispose of properly",
                        "Avoid overhead watering to reduce moisture on leaves",
                        "Apply organic copper fungicide as a preventive measure",
                        "Ensure plants have adequate spacing for airflow",
                        "Clean up fallen leaves and debris regularly"
                    )

                    treatments.forEachIndexed { index, treatment ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Surface(
                                color = Color(0xFF4CAF50).copy(alpha = 0.1f),
                                shape = CircleShape,
                                modifier = Modifier.size(28.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = (index + 1).toString(),
                                        color = Color(0xFF4CAF50),
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = treatment,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatusChip(
    label: String,
    color: Color,
    horizontalPadding: Dp = 14.dp,
    verticalPadding: Dp = 8.dp,
    roundedLevel: Dp = 12.dp
) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.1f), RoundedCornerShape(roundedLevel))
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
    ) {
        Text(label, color = color, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true, heightDp = 1250)
@Composable
fun DiagnosisResultPreview() {
    DoctorPlantTheme {
        DiagnosisScreen(rememberNavController())
    }
}