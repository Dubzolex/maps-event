package fr.itii.ui.events.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Events


@Composable
fun ContainerEvent(
    event: Events,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Nom: ${event.name}",
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "Description : ${event.description}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Type : ${event.type}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Date : ${event.date}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "${event.ville} - ${event.adresse}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "Latitude : ${event.latitude} - Longitude : ${event.longitude}",
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}