package fr.itii.ui.nav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
