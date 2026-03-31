package fr.itii.ui.maps.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import fr.itii.domain.models.collections.Events
import fr.itii.ui.events.sheets.CreateEventSheet
import fr.itii.ui.events.sheets.DetailsEventSheet
import fr.itii.ui.maps.MapsViewModel
import fr.itii.ui.maps.components.CategoryMap
import fr.itii.ui.maps.components.LocationMap
import fr.itii.ui.maps.components.SearchBarMap
import fr.itii.ui.maps.components.TypeMap


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Maps(viewModel: MapsViewModel) {

    val events by viewModel.filteredEvents.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.homePosition, 13f)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Carte en fond
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false,
                mapType = viewModel.currentMapType
            )
        ) {
            events.forEach { event ->
                Marker(
                    state = MarkerState(position = event.location),
                    title = event.name,
                    onClick = {
                        viewModel.selectedEvent = event
                        true
                    }
                )
            }
        }

        // Tout ce qui est flottant au-dessus de la map
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
        ) {
            SearchBarMap(
                value = viewModel.searchText,
                onValueChange = { viewModel.searchText = it }
            )

            CategoryMap(
                selectedCategory = viewModel.selectedCategory,
                onCategorySelected = { category ->
                    viewModel.selectedCategory = category
                },
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 12.dp, start = 12.dp)
        ) {

        }
        // Boutons à droite
        TypeMap(
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 10.dp, bottom = 180.dp),
            onToggleMapType = { viewModel.toggleMapType() }
        )

        // Bouton localisation
        LocationMap(
            modifier = Modifier.align(Alignment.BottomEnd).padding(end = 10.dp, bottom = 110.dp),
            onClick = {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(viewModel.homePosition, 15f))
            }
        )

        // Bouton ajout d'event
        FloatingActionButton(
            onClick = { viewModel.showCreateSheet = true },
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 10.dp, bottom = 10.dp)
        ) {
            Icon(Icons.Default.Add, "Créer")
        }
    }

    // Bottom sheet création
    if (viewModel.showCreateSheet) {
        ModalBottomSheet(onDismissRequest = { viewModel.showCreateSheet = false }) {
            CreateEventSheet(
                onCreate = { viewModel.addEvent(it) },
                onClose = { viewModel.showCreateSheet = false }
            )
        }
    }

    // Bottom sheet détail
    viewModel.selectedEvent?.let { event ->
        ModalBottomSheet(onDismissRequest = { viewModel.selectedEvent = null }) {
            DetailsEventSheet(event = event, onClose = { viewModel.selectedEvent = null })
        }
    }
}