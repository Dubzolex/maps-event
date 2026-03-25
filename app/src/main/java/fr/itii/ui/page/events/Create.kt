package fr.itii.ui.page.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Events
import kotlin.random.Random

@Composable
fun CreateEventSheet(
    onCreateEvent: (Events) -> Unit,
    onClose: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val ville = remember { mutableStateOf("") }
    val adresse = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Créer un événement")

        FormField(label = "Nom", state = name)
        FormField(label = "Type", state = type)
        FormField(label = "Date", state = date)
        FormField(label = "Ville", state = ville)
        FormField(label = "Adresse", state = adresse)

        Button(
            onClick = {
                // Ici je mets une position aléatoire autour de Rouen juste pour tester
                val newEvent = Events(
                    id = Random.nextInt(1000, 9999).toString(),
                    name = name.value,
                    type = type.value,
                    date = date.value,
                    ville = ville.value,
                    adresse = adresse.value,
                    latitude = 49.4431 + Random.nextDouble(-0.03, 0.03),
                    longitude = 1.0993 + Random.nextDouble(-0.03, 0.03),
                    creator = "Utilisateur"
                )

                onCreateEvent(newEvent)
                onClose()
            }
        ) {
            Text("Créer")
        }

        Button(onClick = onClose) {
            Text("Annuler")
        }
    }
}

@Composable
private fun FormField(
    label: String,
    state: MutableState<String>
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}