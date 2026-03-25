package fr.itii.ui.page.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.utils.tables.Events


@Composable
fun MySearchable() {
    // État pour le texte de recherche
    var searchQuery by remember { mutableStateOf("") }

    // Génération d'une liste factice de 10 événements
    val eventsList = List(10) { i ->
        Events(
            name = "Événement $i",
            ville = "Rouen",
            date = "20/03/2026",
            adresse = "$i Rue de la République"
        )
    }




    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // --- BARRE DE RECHERCHE ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.weight(1f),
                label = { Text("Rechercher un événement") },
                placeholder = { Text("Ex: Concert...") },
                singleLine = true
            )

            IconButton(
                onClick = { /* Action de recherche ici */ },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Loupe",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // --- LISTE SCROLLABLE ---
        // LazyColumn est le "Scroll Container" performant pour les listes
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(eventsList) { event ->
                ContainerEvent(event)
            }
        }
    }
}
