package fr.itii.ui.events.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Events


@Composable
fun DetailsEventSheet(
    event: Events,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = event.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(text = "Type : ${event.type}")
        Text(text = "Date : ${event.date}")
        Text(text = "Ville : ${event.ville}")
        Text(text = "Adresse : ${event.adresse}")
        Text(text = "Créateur : ${event.creator}")
        Text(text = "Latitude : ${event.latitude}")
        Text(text = "Longitude : ${event.longitude}")

        Button(onClick = onClose) {
            Text("Fermer")
        }
    }
}