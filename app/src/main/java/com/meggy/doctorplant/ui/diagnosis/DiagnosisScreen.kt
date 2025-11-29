package com.meggy.doctorplant.ui.diagnosis

import android.net.Uri
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
import androidx.compose.material.icons.filled.Emergency
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.meggy.doctorplant.R
import com.meggy.doctorplant.data.model.DiseaseInformation
import com.meggy.doctorplant.data.model.PlantDisease
import com.meggy.doctorplant.ui.theme.BeautifulGreen
import com.meggy.doctorplant.ui.theme.DoctorPlantTheme

@Composable
fun DiagnosisScreen(
    imageUri: Uri,
    uiState: DiagnosisUiState,
    onBackClick: () -> Unit
) {
    when (uiState) {
        DiagnosisUiState.Idle,
        DiagnosisUiState.Loading -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DiagnosisUiState.Error -> {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Erro: ${uiState.message}", color = Color.Red)
                Button(
                    onClick = onBackClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BeautifulGreen,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.height(54.dp)
                ) {
                    Text("Voltar")
                }
            }
        }

        is DiagnosisUiState.Success -> {
            DiagnosisSuccessScreen(
                imageUri = imageUri,
                data = uiState.data,
                scanTime = uiState.scanTime,
            )
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

@Composable
fun DiagnosisSuccessScreen(
    imageUri: Uri,
    data: PlantDisease,
    scanTime: String,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFE0E0E0))
        ) {
            AsyncImage(
                model = imageUri,
                contentDescription = "Foto da planta",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color.Gray),
                placeholder = debugPlaceholder(R.drawable.imagemsoja),
                contentScale = ContentScale.Crop
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
            StatusChip("Identificado", Color(0xFF4CAF50))
            StatusChip(
                label = scanTime.ifEmpty { "-- s Scan" },
                color = Color(0xFF9C27B0)
            )
        }

        Spacer(Modifier.height(16.dp))

        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        text = data.information.name,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                    )

                    Text(
                        text = data.technicalId,
                        color = Color(0xFF757575),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                if (data.diagnosis != "Saudável") {
                    Box(modifier = Modifier.padding(top = 8.dp)) {
                        StatusChip(
                            label = "Doente",
                            Color(0xFFD32F2F),
                            horizontalPadding = 10.dp,
                            verticalPadding = 4.dp,
                            roundedLevel = 20.dp
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text("Nível de confiança", color = Color.Gray, fontSize = 14.sp)
            Spacer(Modifier.height(4.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(data.confidence, color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(8.dp))
                LinearProgressIndicator(
                    progress = { data.confidenceToFloat },
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
                            text = "Descrição da doença",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = data.information.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )

                    Spacer(Modifier.height(16.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Emergency,
                            contentDescription = null,
                            tint = Color(0xFF4CAF50)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Sintomas",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    data.information.symptoms.forEachIndexed { index, symptom ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
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
                                text = symptom,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.DarkGray
                            )
                        }
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.ic_treatment),
                            contentDescription = null,
                            tint = Color(0xFF4CAF50)
                        )
                        Spacer(Modifier.width(6.dp))
                        Text(
                            text = "Tratamento",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }

                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = data.information.treatment,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Composable
fun debugPlaceholder(debugPreviewImage: Int): Painter? {
    if (LocalInspectionMode.current) {
        return painterResource(id = debugPreviewImage)
    }
    return null
}

@Preview(showBackground = true, heightDp = 1250)
@Composable
fun DiagnosisResultPreview() {
    DoctorPlantTheme {
        DiagnosisScreen(
            Uri.EMPTY,
            DiagnosisUiState.Success(
                PlantDisease(
                    diagnosis = "Doente",
                    technicalId = "rust_fungus_01",
                    confidence = "99.29%",
                    information = DiseaseInformation(
                        name = "Ferrugem da Folha",
                        description = "Doença fúngica que afeta o processo de fotossíntese da planta, criando manchas alaranjadas.",
                        symptoms = listOf(
                            "Pequenas pústulas (bolinhas) na parte de baixo da folha.",
                            "Cor amarelada que evolui para marrom-escuro.",
                            "Desfolha precoce.",
                            "Em casos graves, pode ocorrer morte da planta."
                        ),
                        treatment = "Remover as folhas infectadas imediatamente e aplicar fungicida à base de cobre."
                    )
                ),
                scanTime = "10s"
            )
        ) {}
    }
}