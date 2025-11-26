package com.example.doctorplant.ui.history

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.doctorplant.R
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.ui.components.TopBar
import com.example.doctorplant.ui.theme.GreenHome
import com.example.doctorplant.utils.TimeUtils
import com.example.doctorplant.utils.TimeUtils.isThisWeek
import com.example.doctorplant.utils.TimeUtils.isToday

@Composable
fun HistoryScreen(
    historyItems: List<DiagnosisHistory>,
    onItemClick: (DiagnosisHistory) -> Unit,
    onDeleteItems: (List<DiagnosisHistory>) -> Unit
) {
    // 1. Estado do Filtro
    var selectedFilter by remember { mutableStateOf(TimeUtils.HistoryFilter.ALL) }

    // 2. Estado de Seleção (Multi-select para deletar)
    var selectedItems by remember { mutableStateOf(setOf<DiagnosisHistory>()) }
    val isSelectionMode = selectedItems.isNotEmpty()

    val filteredList = remember(historyItems, selectedFilter) {
        when (selectedFilter) {
            TimeUtils.HistoryFilter.ALL -> historyItems
            TimeUtils.HistoryFilter.TODAY -> historyItems.filter { isToday(it.date) }
            TimeUtils.HistoryFilter.WEEK -> historyItems.filter { isThisWeek(it.date) }
        }
    }

    val totalScans = historyItems.size
    val totalDiseased = historyItems.count { it.diagnosisStatus != "Saudável" }

    val accuracyRate = remember(historyItems) {
        if (historyItems.isNotEmpty()) {
            historyItems.map {
                it.confidence
                    .replace("%", "")
                    .trim()
                    .toFloatOrNull()
                    ?: 0f
            }.average().toInt()
        } else {
            0
        }
    }

    fun toggleSelection(item: DiagnosisHistory) {
        selectedItems = if (selectedItems.contains(item)) {
            selectedItems - item
        } else {
            selectedItems + item
        }
    }

    Scaffold(
        topBar = {
            if (isSelectionMode) {
                TopBar(
                    title = "${selectedItems.size} selecionado(s)",
                    navigationIcon = Icons.Default.Close,
                    onNavigationClick = { selectedItems = emptySet() },
                    actions = {
                        IconButton(onClick = {
                            onDeleteItems(selectedItems.toList())
                            selectedItems = emptySet()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Excluir")
                        }
                    }
                )
            } else {
                TopBar(
                    title = "Histórico",
                    navigationIcon = null,
                    onNavigationClick = null
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA)),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        FilterButton(
                            text = "Tudo",
                            selected = selectedFilter == TimeUtils.HistoryFilter.ALL,
                            onClick = { selectedFilter = TimeUtils.HistoryFilter.ALL }
                        )
                        FilterButton(
                            text = "Hoje",
                            selected = selectedFilter == TimeUtils.HistoryFilter.TODAY,
                            onClick = { selectedFilter = TimeUtils.HistoryFilter.TODAY }
                        )
                        FilterButton(
                            text = "Essa semana",
                            selected = selectedFilter == TimeUtils.HistoryFilter.WEEK,
                            onClick = { selectedFilter = TimeUtils.HistoryFilter.WEEK }
                        )
                    }
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(GreenHome)
                            .padding(vertical = 18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatItem(value = totalScans.toString(), label = "Total de diagnósticos", color = Color.White)
                            StatItem(value = totalDiseased.toString(), label = "Plantas doentes", color = Color.White)
                            StatItem(value = "$accuracyRate%", label = "Acurácia Média", color = Color.White)
                        }
                    }
                }

                if (filteredList.isEmpty()) {
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
                    items(filteredList) { item ->
                        val isSelected = selectedItems.contains(item)
                        HistoryCard(
                            item = item,
                            isSelected = isSelected,
                            onClick = {
                                if (isSelectionMode) {
                                    toggleSelection(item)
                                } else {
                                    onItemClick(item)
                                }
                            },
                            onLongClick = {
                                if (!isSelectionMode) {
                                    toggleSelection(item)
                                }
                            },
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryCard(
    item: DiagnosisHistory,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier
) {
    val isDiseased = item.diagnosisStatus != "Saudável"
    val (dotColor, bgColor) = if (isDiseased) {
        Color.Red to Color(0xFFFFEBEE)
    } else {
        Color(0xFF4CAF50) to Color(0xFFE8F5E9)
    }
    val advice = if (isDiseased) {
        "Necessita de tratamento"
    } else {
        "Nenhum tratamento necessário"
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier.fillMaxWidth()
            .combinedClickable(
            onClick = onClick,
            onLongClick = onLongClick
        )
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
                            Text("Doente", color = dotColor, fontSize = 12.sp)
                        } else {
                            Text("Saudável", color = dotColor, fontSize = 12.sp)
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
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Selecionado",
                        tint = Color.Blue,
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.White, CircleShape)
                    )
                }
            }
        }
    }
}

@Composable
fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    if (selected) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = GreenHome),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Icon(Icons.Default.DateRange, null, tint = Color.White, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(4.dp))
            Text(text, color = Color.White, fontSize = 13.sp)
        }
    } else {
        OutlinedButton(
            onClick = onClick,
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
        onItemClick = {},
        onDeleteItems = {}
    )
}
