package com.example.doctorplant.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.doctorplant.ui.theme.Disable

object GenericButtonDefaults {
    private val ButtonHorizontalPadding = 16.dp
    private val ButtonVerticalPadding = 12.dp

    val ContentPadding = PaddingValues(
        start = ButtonHorizontalPadding,
        top = ButtonVerticalPadding,
        end = ButtonHorizontalPadding,
        bottom = ButtonVerticalPadding
    )
}

@Composable
fun GenericOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    onClick: () -> Unit,
    textBold: Boolean = false,
    enabled: Boolean = true,
    buttonLarge: Boolean = false,
    isLoading: Boolean = false,
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color.White),
        modifier = modifier
    ) {
        if (isLoading) {
            GenericLoading()
        } else {
            Text(
                text,
                color = textColor,
                textAlign = TextAlign.Center,
                fontSize = if (buttonLarge || textBold) 16.sp else 14.sp,
                fontWeight = if (buttonLarge || textBold) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.padding(if (buttonLarge) 8.dp else 0.dp),
            )
        }
    }
}

@Composable
fun GenericButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    buttonLarge: Boolean = false,
    icon: Int? = null,
    style: GenericButtonStyle = GenericButtonStyle.Filled,
    contentColor: Color = Color.White,
    outlineColor: Color = Color.White,
    containerColor : Color = Color.White
) {
    val content: @Composable RowScope.() -> Unit = {
        if (icon != null) {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = when (style) {
                    GenericButtonStyle.Filled -> contentColor
                    GenericButtonStyle.Outlined -> outlineColor
                    GenericButtonStyle.Disable -> Disable
                }
            )
        }
        if (text.isNotEmpty()) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = text,
                style = typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(if (buttonLarge) 6.dp else 0.dp),
                color = when (style) {
                    GenericButtonStyle.Filled -> contentColor
                    GenericButtonStyle.Outlined -> outlineColor
                    GenericButtonStyle.Disable -> Disable
                }
            )
        }
    }

    when (style) {
        GenericButtonStyle.Filled -> {
            Button(
                onClick = onClick,
                enabled = enabled,
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.buttonColors(containerColor = containerColor),
                contentPadding = GenericButtonDefaults.ContentPadding,
                modifier = modifier,
                content = content
            )
        }

        GenericButtonStyle.Outlined -> {
            OutlinedButton(
                onClick = onClick,
                enabled = enabled,
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(1.dp, outlineColor),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = outlineColor,
                    disabledContentColor = outlineColor.copy(alpha = 0.6f),
                ),
                contentPadding = GenericButtonDefaults.ContentPadding,
                modifier = modifier,
                content = content
            )
        }

        GenericButtonStyle.Disable -> {
            OutlinedButton(
                onClick = onClick,
                enabled = enabled,
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(1.dp, Disable),
                modifier = modifier,
                content = content
            )
        }
    }
}

enum class GenericButtonStyle { Filled, Outlined, Disable }