package fr.itii.ui.page.maps

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryRowMap(
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CategoryChip(
            label = "Domicile",
            isSelected = selectedCategory == "Domicile",
            onClick = { onCategorySelected("Domicile") },
            icon = {
                Icon(Icons.Default.Home, contentDescription = null)
            }
        )

        CategoryChip(
            label = "Restaurants",
            isSelected = selectedCategory == "Restaurants",
            onClick = { onCategorySelected("Restaurants") },
            icon = {
                Icon(Icons.Default.LocalDining, contentDescription = null)
            }
        )

        CategoryChip(
            label = "Parcs",
            isSelected = selectedCategory == "Parcs",
            onClick = { onCategorySelected("Parcs") },
            icon = {
                Icon(Icons.Default.Park, contentDescription = null)
            }
        )

        CategoryChip(
            label = "Tout",
            isSelected = selectedCategory == "Tout",
            onClick = { onCategorySelected("Tout") },
            icon = {
                Icon(Icons.Default.Place, contentDescription = null)
            }
        )
    }
}

@Composable
private fun CategoryChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    AssistChip(
        onClick = onClick,
        label = { Text(label) },
        leadingIcon = icon,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (isSelected) Color.White else Color.White,
            labelColor = Color.Black,
            leadingIconContentColor = Color.Black
        )
    )
}