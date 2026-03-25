package fr.itii.ui.container

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun Navigation(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(
                icon = Icons.Filled.LocationOn,
                label = "Maps",
                isSelected = selectedItem == 0,
                onClick = { onItemSelected(0) }
            )
            NavigationItem(
                icon = Icons.Filled.Search,
                label = "Search",
                isSelected = selectedItem == 1,
                onClick = { onItemSelected(1) }
            )
            NavigationItem(
                icon = Icons.Filled.AccountCircle,
                label = "Profil",
                isSelected = selectedItem == 2,
                onClick = { onItemSelected(2) }
            )
        }
    }
}



@Composable
fun NavigationItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // On définit la couleur de la bulle
    val background = if (isSelected) Color.Blue else Color.Transparent
    val contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant

    // On utilise une Box avec un Modifier.clip pour faire la forme de bulle
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp)) // Bords très arrondis pour la bulle
            //.background(background)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .width(80.dp), // Espace interne de la bulle
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if(isSelected) {
                Icon(icon, contentDescription = null, tint = background)
                Text(text = label, color = background, fontWeight = FontWeight.Bold)
            } else {
                Icon(icon, contentDescription = null, tint = contentColor)
                Text(text = label, color = contentColor)
            }

        }
    }
}
