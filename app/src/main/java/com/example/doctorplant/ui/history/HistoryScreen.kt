package com.example.doctorplant.ui.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Circle
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
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.doctorplant.R
import com.example.doctorplant.data.model.DiagnosisHistory
import com.example.doctorplant.ui.components.TopBar
import com.example.doctorplant.ui.theme.BeautifulGreen
import com.example.doctorplant.utils.TimeUtils

sealed interface HistoryEvent {
    data class ChangeFilter(val filter: TimeUtils.HistoryFilter) : HistoryEvent
    data class ToggleSelection(val item: DiagnosisHistory) : HistoryEvent
    object ClearSelection : HistoryEvent
    object DeleteSelected : HistoryEvent
}
@Composable
fun HistoryScreen(
    state: HistoryState,
    onItemClick: (DiagnosisHistory) -> Unit,
    onEvent: (HistoryEvent) -> Unit
) {
    Scaffold(
        topBar = {
            if (state.isSelectionMode) {
                TopBar(
                    title = "${state.selectedItems.size} selecionado(s)",
                    navigationIcon = Icons.Default.Close,
                    onNavigationClick = { onEvent(HistoryEvent.ClearSelection) },
                    actions = {
                        IconButton(onClick = { onEvent(HistoryEvent.DeleteSelected) }) {
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
                    .background(color = Color(0xFFF8F9FA)),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Filters(state, onEvent)
                }

                item {
                    Statistics(state)
                }

                if (state.historyItems.isEmpty()) {
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
                    items(state.historyItems) { item ->
                        val isSelected = state.selectedItems.contains(item)

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp)
                                .animateContentSize()
                        ) {
                            AnimatedVisibility(
                                visible = state.isSelectionMode,
                                enter = expandHorizontally() + fadeIn(),
                                exit = shrinkHorizontally() + fadeOut()
                            ) {
                                Icon(
                                    imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Outlined.Circle,
                                    contentDescription = "Selecionar",
                                    tint = if (isSelected) BeautifulGreen else Color.Gray,
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .clickable { onEvent(HistoryEvent.ToggleSelection(item)) }
                                )
                            }

                            HistoryCard(
                                item = item,
                                isSelected = isSelected,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp),
                                onClick = {
                                    if (state.isSelectionMode) {
                                        onEvent(HistoryEvent.ToggleSelection(item))
                                    } else {
                                        onItemClick(item)
                                    }
                                },
                                onLongClick = {
                                    if (!state.isSelectionMode) {
                                        onEvent(HistoryEvent.ToggleSelection(item))
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Filters(
    state: HistoryState,
    onEvent: (HistoryEvent) -> Unit
) {
    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FilterButton(
            text = "Tudo",
            selected = state.selectedFilter == TimeUtils.HistoryFilter.ALL,
            onClick = { onEvent(HistoryEvent.ChangeFilter(TimeUtils.HistoryFilter.ALL)) }
        )
        FilterButton(
            text = "Hoje",
            selected = state.selectedFilter == TimeUtils.HistoryFilter.TODAY,
            onClick = { onEvent(HistoryEvent.ChangeFilter(TimeUtils.HistoryFilter.TODAY)) }
        )
        FilterButton(
            text = "Essa semana",
            selected = state.selectedFilter == TimeUtils.HistoryFilter.WEEK,
            onClick = { onEvent(HistoryEvent.ChangeFilter(TimeUtils.HistoryFilter.WEEK)) }
        )
    }
}

@Composable
fun Statistics(state: HistoryState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BeautifulGreen)
            .padding(vertical = 18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(value = state.totalScans.toString(), label = "Total de diagnósticos", color = Color.White)
            StatItem(value = state.totalDiseased.toString(), label = "Plantas doentes", color = Color.White)
            StatItem(value = "${state.accuracyRate}%", label = "Acurácia Média", color = Color.White)
        }
    }
}

@Composable
fun HistoryCard(
    item: DiagnosisHistory,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
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

    val cardContainerColor = if (isSelected) Color(0xFFC8E6C9) else Color.White

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardContainerColor),
        elevation = CardDefaults.cardElevation(if (isSelected) 0.dp else 2.dp),
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
                model = item.imageUri.toUri(),
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
            colors = ButtonDefaults.buttonColors(containerColor = BeautifulGreen),
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
    HistoryScreen(
        state = HistoryState(),
        onItemClick = {},
        onEvent = {}
    )
}
