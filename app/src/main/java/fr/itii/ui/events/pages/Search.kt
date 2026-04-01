package fr.itii.ui.events.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.FilterListOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import fr.itii.ui.events.EventViewModel
import fr.itii.ui.events.sheets.DetailsEventSheet
import fr.itii.ui.events.sheets.FilterEventSheet


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(viewModel: EventViewModel) {

    val events by viewModel.filteredEvents.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // --- BARRE DE RECHERCHE ---
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if(viewModel.isFilter) {
                IconButton(
                    onClick = {
                        viewModel.typeState = "Tout"
                        viewModel.distanceState = 0f
                        viewModel.isFilter = false
                        viewModel.onApplyFilters()
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        viewModel.showFilterSheet = true
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterListOff,
                        contentDescription = "Filter",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            OutlinedTextField(
                value = viewModel.searchQuery,
                onValueChange = { viewModel.searchQuery = it },
                modifier = Modifier.weight(1f),
                label = { Text("Rechercher...") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search), // Affiche une loupe sur le clavier
                keyboardActions = KeyboardActions(
                    onSearch = { viewModel.onApplyFilters() } // Déclenche au clic sur "Entrée"
                )
            )

            IconButton(
                onClick = {
                    viewModel.onApplyFilters()
                },
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
            items(events) { event ->
                ContainerEvent(
                    event = event,
                    modifier = Modifier.clickable {
                        // On enregistre l'événement cliqué
                        viewModel.selectedEvent = event
                    }
                )
            }
        }

        if (viewModel.showFilterSheet) {
            ModalBottomSheet(
                // Correction : on met à false pour pouvoir fermer la sheet
                onDismissRequest = { viewModel.showFilterSheet = false }
            ) {
                FilterEventSheet(
                    initialType = viewModel.typeState,
                    initialDistance = viewModel.distanceState,
                    onApply = { newType, newDist ->
                        viewModel.typeState = newType
                        viewModel.distanceState = newDist
                        viewModel.isFilter = true
                        viewModel.onApplyFilters() // On lance la recherche
                        viewModel.showFilterSheet = false
                    },
                    onClose = { viewModel.showFilterSheet = false }
                )
            }
        }

        // Si un événement est sélectionné, on ouvre la fiche
        viewModel.selectedEvent?.let { event ->
            ModalBottomSheet(
                onDismissRequest = { viewModel.selectedEvent = null }
            ) {
                // Ton composant qui affiche les infos de l'événement
                DetailsEventSheet(
                    event = event,
                    onClose = { viewModel.selectedEvent = null }
                )
            }
        }
    }
}
