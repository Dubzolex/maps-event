package fr.itii.ui.maps

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapType
import fr.itii.ui.events.EventRepository
import fr.itii.domain.models.collections.Events
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class MapsViewModel(private val repository: EventRepository) : ViewModel() {

    // Constante (on pourrait aussi la récupérer via une LiveData/Flow si elle change)
    var homePosition by mutableStateOf(LatLng(0.0, 0.0))
    var temporaryLocation by mutableStateOf(LatLng(0.0, 0.0))

    // --- ÉTATS DE FILTRAGE ---
    var searchText by mutableStateOf("")
    var selectedCategory by mutableStateOf("Tout")
    var currentMapType by mutableStateOf(MapType.NORMAL)

    // --- ÉTATS D'INTERFACE (UI) ---
    var selectedEvent by mutableStateOf<Events?>(null)
    var showCreateSheet by mutableStateOf(false)
    var showModifySheet by mutableStateOf(false)
    var showDetailsSheet by mutableStateOf(false)



    // --- LOGIQUE DE FILTRAGE RÉACTIVE ---
    // On observe les events du repo et on applique les filtres en temps réel


    val filteredEvents: StateFlow<List<Events>> = repository.eventsList
        .combine(snapshotFlow { searchText }) { events, query ->
            if (query.isEmpty()) {
                events
            } else {
                // On appelle la fonction 'filter' du repository
                repository.filter(events, query) { event ->
                    // Voici le 'selector' : on fusionne les champs pour la recherche
                    "${event.name} ${event.ville} ${event.adresse}"
                }
            }
        }
        .combine(snapshotFlow { selectedCategory }) { filteredBySearch, category ->
            // 2. On applique ensuite le filtre de catégorie
            filteredBySearch.filter { event ->
                when (category) {
                    "Restaurant" -> event.type.equals("Restaurant", ignoreCase = true)
                    "Parc" -> event.type.equals("Parc", ignoreCase = true)
                    "Concert" -> event.type.equals("Concert", ignoreCase = true)
                    "Festival" -> event.type.equals("Festival", ignoreCase = true)
                    "Monument" -> event.type.equals("Monument", ignoreCase = true)
                    "Ecole" -> event.type.equals("Ecole", ignoreCase = true)
                    else -> true
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    @SuppressLint("MissingPermission")
    fun searchHomePosition(context: Context) {
        // On initialise le client avec le contexte passé en paramètre
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                // Mise à jour de la position
                homePosition = LatLng(location.latitude, location.longitude)
            } else {
                // Optionnel : Gérer le cas où la localisation est désactivée sur le tel
                println("Localisation non trouvée (GPS peut-être éteint)")
            }
        }.addOnFailureListener { exception ->
            // Gérer l'erreur de récupération
            exception.printStackTrace()
        }
    }

    // --- ACTIONS ---
    fun toggleMapType() {
        currentMapType = when (currentMapType) {
            MapType.NORMAL -> MapType.SATELLITE
            MapType.SATELLITE -> MapType.TERRAIN
            else -> MapType.NORMAL
        }
    }

    fun addEvent(event: Events) {
        viewModelScope.launch {
            // On appelle la fonction add du repo
            repository.add(event) { success, message ->
                // On utilise le résultat pour fermer la sheet ou afficher une erreur
                if (success) {
                    showCreateSheet = false
                } else {
                    // Optionnel : tu peux logger l'erreur ou mettre à jour un uiMessage
                    Log.e("MapsViewModel", "Erreur ajout : $message")
                }
            }
        }
    }

    fun updateEvent(event: Events, context: Context) {
        repository.update(event) { success, message ->
            if (success) {
                showModifySheet = false
                showDetailsSheet = false // Ferme la ficheSheet
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}