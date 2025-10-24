package com.example.doctorplant.ui.learnmore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctorplant.R

@Composable
fun LearnMoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8))
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFF2E7D32), Color(0xFF4CAF50))
                    ),
                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                )
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "About Dr. Plant",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "Advanced AI-powered plant disease recognition system developed by passionate researchers",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_leaf),
                            contentDescription = null,
                            tint = Color(0xFF2E7D32)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Project Overview",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color(0xFF2E7D32)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Dr. Plant is an innovative mobile application that uses artificial intelligence and machine learning to identify plant diseases from photographs. Our system can detect various plant ailments and provide treatment recommendations to help farmers and gardeners protect their crops.",
                        color = Color.Black.copy(alpha = 0.7f),
                        letterSpacing = 0.5.sp,
                        lineHeight = 18.sp,
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "\uD83D\uDCC5 Projetos em Computação II • 2025",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("Development Team")
            TeamMember(
                name = "Bruno Moreira",
                role = "Mobile Developer",
                specialty = "Android App",
                specialtyColor = Color(0xFF2E7D32)
            )
            TeamMember(
                name = "Victor Mariano Rocha",
                role = "AI Developer",
                specialty = "Segmentation and Machine Learning",
                specialtyColor = Color(0xFF1565C0)
            )
            TeamMember(
                name = "Julia Amadio",
                role = "Backend Developer",
                specialty = "API Integration and AI Tools",
                specialtyColor = Color(0xFFD32F2F)
            )

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("Academic Supervision")
            TeamMember(
                name = "Prof. Pedronette",
                role = "Supervising Professor",
                specialty = "Computer Science Department",
                specialtyColor = Color(0xFF388E3C)
            )

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = Color(0xFF3949AB)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text("UNESP Rio Claro", fontWeight = FontWeight.Bold)
                            Text(
                                "Universidade Estadual Paulista",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                    Text(
                        text = "This project is developed at the IGCE - UNESP Rio Claro campus, as part of our commitment to innovative research in agricultural technology.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = "Rio Claro, São Paulo, Brazil",
                        color = Color(0xFF1E88E5),
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Key Features", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FeatureItem(
                    "Photo Analysis",
                    "Instant disease detection",
                    R.drawable.ic_camera,
                    Color(0xFF4CAF50),
                    modifier = Modifier.weight(1f)
                )
                FeatureItem(
                    "AI Powered",
                    "Machine learning algorithms",
                    R.drawable.ic_brain,
                    Color(0xFF2196F3),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FeatureItem(
                    "Treatment Tips", "Expert recommendations",
                    R.drawable.ic_treatment,
                    Color(0xFFAB47BC),
                    modifier = Modifier.weight(1f)
                )
                FeatureItem(
                    "History",
                    "Track your plants",
                    R.drawable.ic_history,
                    Color(0xFFFFA726),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF212121), shape = RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text("Get in Touch", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(
                        "Questions about our research or collaboration opportunities?",
                        color = Color.LightGray,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ContactItem(Icons.Default.Email, "bruno.moreira1@unesp.br")
                    ContactItem(Icons.Default.Email, "vm.rocha@unesp.br")
                    ContactItem(Icons.Default.Email, "julia.amadio@unesp.br")
                    ContactItem(Icons.Default.Link, "https://www.rc.unesp.br")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    )
}

@Composable
fun TeamMember(
    name: String,
    role: String,
    specialty: String,
    specialtyColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(role, color = Color.Gray, fontSize = 13.sp)
                Text(
                    specialty,
                    color = specialtyColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun FeatureItem(title: String, desc: String, iconRes: Int, color: Color, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxHeight()
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text(desc, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Composable
fun ContactItem(imageVector: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            tint = Color(0xFF81C784),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.White, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview(showBackground = true, heightDp = 1400)
@Composable
fun LearnMoreScreenPreview() {
    LearnMoreScreen()
}
