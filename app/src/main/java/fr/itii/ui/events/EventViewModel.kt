package fr.itii.ui.events

import fr.itii.domain.models.collections.Events

class EventViewModel(private val repository: EventRepository) {
    val eventsList: List<Events> = repository.eventsList


}