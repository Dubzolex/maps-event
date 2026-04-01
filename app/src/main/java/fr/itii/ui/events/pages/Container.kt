package fr.itii.ui.events.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Events


@Composable
fun ContainerEvent(
    event: Events,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = event.name,
                style = MaterialTheme.typography.titleLarge
            )
/*
            Text(
                text = "Description : ${event.description}",
                style = MaterialTheme.typography.bodyMedium
            )*/

            Text(
                text = "${event.ville}${if (!event.ville.isNullOrBlank() && !event.adresse.isNullOrBlank()) " - " else ""}${event.adresse}",
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Text(
                    text = "${event.type}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
/*
                Text(
                    text = "Date : ${event.date}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )*/
            }
/*
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Text(
                    text = "Latitude : ${event.latitude.toString().take(8)}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Longitude : ${event.longitude.toString().take(8)}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
            }*/

        }
    }
}