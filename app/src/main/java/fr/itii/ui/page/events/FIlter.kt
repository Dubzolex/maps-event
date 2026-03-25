package fr.itii.ui.page.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterSheet(
    selectedType: MutableState<String>,
    distanceKm: MutableFloatState,
    onApply: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Filtres")

        OutlinedTextField(
            value = selectedType.value,
            onValueChange = { selectedType.value = it },
            label = { Text("Type d'événement") },
            placeholder = { Text("Concert, Sport, Théâtre...") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Distance max : ${distanceKm.floatValue.toInt()} km")

        Slider(
            value = distanceKm.floatValue,
            onValueChange = { distanceKm.floatValue = it },
            valueRange = 1f..100f
        )

        Button(onClick = onApply) {
            Text("Appliquer")
        }

        Button(onClick = onClose) {
            Text("Fermer")
        }
    }
}