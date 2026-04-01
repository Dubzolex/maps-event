package fr.itii.ui.events.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.ui.components.ActionButton
import fr.itii.ui.components.NeutralButton
import fr.itii.ui.components.SelectField


@Composable
fun FilterEventSheet(
    initialType: String,
    initialDistance: Float,
    onApply: (String, Float) -> Unit, // On renvoie les nouvelles valeurs
    onClose: () -> Unit
) {
    // 1. On crée des copies locales modifiables des paramètres reçus
    var tempType by remember { mutableStateOf(initialType) }
    var tempDistance by remember { mutableFloatStateOf(initialDistance) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Filtres", style = MaterialTheme.typography.headlineSmall)

        // On utilise la valeur locale 'tempType'
        SelectField(
            label = "Type",
            value = tempType,
            onValueChange = { tempType = it },
            options = listOf("Restaurant", "Parc", "Concert", "Festival", "Monument", "Ecole", "Tout")
        )

        // On affiche la valeur locale 'tempDistance'
        Text(text = "Distance max : ${tempDistance.toInt()} km")

        Slider(
            value = tempDistance,
            onValueChange = { tempDistance = it },
            valueRange = 0f..100f
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacement propre entre boutons
        ) {
            // Bouton Annuler/Fermer
            NeutralButton(
                text = "Annuler",
                onClick = onClose
            )

            // Bouton Appliquer (Renvoie les données temporaires au ViewModel)
            ActionButton(
                text = "Appliquer",
                onClick = { onApply(tempType, tempDistance) }
            )
        }
    }
}
