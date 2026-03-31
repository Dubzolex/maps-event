package fr.itii.ui.maps

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import fr.itii.domain.models.collections.Events
import fr.itii.ui.events.EventRepository


class MapsViewModel(private val repository: EventRepository) : ViewModel() {

    val eventsList = repository.eventsList

    val db = Firebase.firestore


    init {
        loadFakeEvents()
    }





    }

    fun addEvent(event: Events) {
        eventsList.add(event)
    }
}
