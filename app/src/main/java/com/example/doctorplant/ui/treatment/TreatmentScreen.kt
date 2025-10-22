package com.example.doctorplant.ui.treatment

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.doctorplant.ui.theme.DoctorPlantTheme

@Composable
fun TreatmentScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FB))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            SectionHeader(
                icon = Icons.Default.Search,
                title = "Detected Symptoms"
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SymptomCard(
                    "Dark Brown Spots",
                    "Irregular circular lesions with yellow halos.",
                    "Severe",
                    Color(0xFFFFE5E5)
                )
                SymptomCard(
                    "Leaf Yellowing",
                    "Progressive chlorosis spreading from infected area.",
                    "Moderate",
                    Color(0xFFFFF5E1)
                )
                SymptomCard(
                    "Bacterial Ooze",
                    "Milky exudate visible in early morning hours.",
                    "Mild",
                    Color(0xFFFFF8E5)
                )
            }
        }

        item {
            SectionHeader(
                icon = Icons.Default.ShoppingCart,
                title = "Treatment Plan"
            )

            // Immediate Actions
            SubsectionHeader("Immediate Actions", "Urgent", Color(0xFFFFE5E5))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ActionCard("Remove Infected Leaves", "Immediately prune and dispose of affected foliage.")
                ActionCard("Isolate Plant", "Separate from healthy plants to prevent spread.")
            }
        }

        item {
            SectionHeader(
                icon = Icons.Default.ShoppingCart,
                title = "Prevention Guidelines"
            )

            PreventionCard(
                title = "Water Management",
                color = Color(0xFFE3F2FD),
                tips = listOf(
                    "Avoid overhead watering, use drip irrigation.",
                    "Water early morning to allow leaves to dry.",
                    "Ensure proper drainage around plants."
                )
            )

            PreventionCard(
                title = "Air Circulation",
                color = Color(0xFFE8F5E9),
                tips = listOf(
                    "Space plants adequately for airflow.",
                    "Prune regularly to reduce leaf density.",
                    "Avoid overcrowding in greenhouse settings."
                )
            )

            PreventionCard(
                title = "Tool Sanitation",
                color = Color(0xFFFFF3E0),
                tips = listOf(
                    "Disinfect pruning tools between plants.",
                    "Use 70% alcohol or bleach solution.",
                    "Clean hands thoroughly after handling."
                )
            )
        }
    }
}

@Composable
fun SectionHeader(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 4.dp)) {
        Icon(icon, contentDescription = null, tint = Color(0xFF3C3C3C))
        Spacer(Modifier.width(8.dp))
        Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}

@Composable
fun SubsectionHeader(title: String, tag: String? = null, tagColor: Color? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
    ) {
        Text(title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        if (tag != null && tagColor != null) {
            Spacer(Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .background(tagColor, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(tag, color = Color.Black, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SymptomCard(name: String, desc: String, level: String, bgColor: Color) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(name, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(desc, fontSize = 13.sp)
            Text("Severity: $level", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ActionCard(title: String, desc: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Text(desc, fontSize = 13.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun PreventionCard(title: String, color: Color, tips: List<String>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(Modifier.padding(12.dp)) {
            Text(title, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            tips.forEach { tip ->
                Row(Modifier.padding(vertical = 2.dp)) {
                    Text("• ", fontWeight = FontWeight.Bold)
                    Text(tip, fontSize = 13.sp)
                }
            }
        }
    }
}

@Preview(heightDp = 1100)
@Composable
fun TreatmentScreenPreview() {
    DoctorPlantTheme {
        TreatmentScreen(rememberNavController())
    }
}