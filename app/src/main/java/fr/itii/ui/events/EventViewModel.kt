package fr.itii.ui.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.itii.domain.models.collections.Events
import fr.itii.domain.models.collections.Users
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EventViewModel(private val repository: EventRepository)  : ViewModel() {

    var searchQuery by mutableStateOf("")
    var typeState by mutableStateOf("")
    var distanceState by mutableFloatStateOf(0f)

    // Dans ton EventViewModel.kt
    var selectedEvent by mutableStateOf<Events?>(null)

    var showFilterSheet by mutableStateOf(false)
    var showDetailsSheet by mutableStateOf(false)
    var isFilter by mutableStateOf(false)


    // Le déclencheur : on émet une valeur dedans quand on clique sur le bouton
    private val filterTrigger = MutableSharedFlow<Unit>(replay = 1)

    // Initialisation du trigger pour charger la liste au début
    init {
        filterTrigger.tryEmit(Unit)
    }

    // La liste filtrée qui écoute le "trigger"
    val filteredEvents: StateFlow<List<Events>> = filterTrigger
        .flatMapLatest { // On attend le signal du bouton
            repository.eventsList.map { allEvents ->
                // Ici on applique toute la logique de tri
                var result = allEvents

                // Filtre Recherche
                if (searchQuery.isNotEmpty()) {
                    result = result.filter {
                        it.name.contains(searchQuery, ignoreCase = true) ||
                        it.ville.contains(searchQuery, ignoreCase = true) ||
                        it.adresse.contains(searchQuery, ignoreCase = true)
                    }
                }

                // Filtre Type
                if (typeState.isNotEmpty() && typeState != "Tout") {
                    result = result.filter { it.type.equals(typeState, ignoreCase = true) }
                }

                result // On renvoie la liste finale triée
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // La fonction à appeler sur le onClick du bouton
    fun onApplyFilters() {
        filterTrigger.tryEmit(Unit)
    }
}