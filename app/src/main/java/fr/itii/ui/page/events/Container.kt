package fr.itii.ui.page.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Events

@Composable
fun ContainerEvent(event: Events) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = event.name, style = MaterialTheme.typography.titleLarge, color = Color.Black)
            Text(text = "${event.date}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "${event.ville} - ${event.adresse}", style = MaterialTheme.typography.bodySmall)
        }
    }
}