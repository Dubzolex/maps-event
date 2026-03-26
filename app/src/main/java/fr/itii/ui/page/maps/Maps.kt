package fr.itii.ui.page.maps

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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import fr.itii.domain.models.collections.Events
import fr.itii.ui.page.events.CreateEventSheet
import fr.itii.ui.page.events.EventDetails


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Maps(viewModel: MapsViewModel) {
    val homePosition = LatLng(49.4431, 1.0993)

    var selectedEvent by remember { mutableStateOf<Events?>(null) }
    var showCreateSheet by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Tout") }
    var currentMapType by remember { mutableStateOf(MapType.NORMAL) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(homePosition, 13f)
    }

    // Filtre des événements
    val filteredEvents = viewModel.eventsList.filter { event ->
        val matchesSearch =
            event.name.contains(searchText, ignoreCase = true) ||
                    event.ville.contains(searchText, ignoreCase = true) ||
                    event.adresse.contains(searchText, ignoreCase = true)

        val matchesCategory = when (selectedCategory) {
            "Tout" -> true
            "Domicile" -> true // Ici on garde tout, et le bouton recentre juste la map
            "Restaurants" -> event.type.equals("Restaurant", ignoreCase = true)
            "Parcs" -> event.type.equals("Parc", ignoreCase = true)
            else -> true
        }

        matchesSearch && matchesCategory
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Carte en fond
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = false,
                mapType = currentMapType
            )
        ) {
            filteredEvents.forEach { event ->
                Marker(
                    state = MarkerState(position = event.location),
                    title = event.name,
                    snippet = "${event.ville} - ${event.date}",
                    onClick = {
                        selectedEvent = event
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
                value = searchText,
                onValueChange = { searchText = it }
            )

            CategoryRowMap(
                selectedCategory = selectedCategory,
                onCategorySelected = { category ->
                    selectedCategory = category

                    // Action spéciale "Domicile" : recentre la caméra
                    if (category == "Domicile") {
                        cameraPositionState.position =
                            CameraPosition.fromLatLngZoom(homePosition, 15f)
                    }
                },
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        // Boutons à droite
        RightMapButtons(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp),
            onToggleMapType = {
                currentMapType = when (currentMapType) {
                    MapType.NORMAL -> MapType.SATELLITE
                    MapType.SATELLITE -> MapType.TERRAIN
                    else -> MapType.NORMAL
                }
            }
        )

        // Bouton localisation
        MyLocationButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 90.dp),
            onClick = {
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(homePosition, 15f)
            }
        )

        // Bouton ajout d'event
        FloatingActionButton(
            onClick = { showCreateSheet = true },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Créer un événement"
            )
        }
    }

    // Bottom sheet création
    if (showCreateSheet) {
        ModalBottomSheet(
            onDismissRequest = { showCreateSheet = false }
        ) {
            CreateEventSheet(
                onCreateEvent = { event ->
                    viewModel.addEvent(event)
                },
                onClose = { showCreateSheet = false }
            )
        }
    }

    // Bottom sheet détail
    if (selectedEvent != null) {
        ModalBottomSheet(
            onDismissRequest = { selectedEvent = null }
        ) {
            EventDetails(
                event = selectedEvent!!,
                onClose = { selectedEvent = null }
            )
        }
    }
}