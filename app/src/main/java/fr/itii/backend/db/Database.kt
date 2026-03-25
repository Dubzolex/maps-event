package fr.itii.backend.db

import com.google.firebase.firestore.auth.User
import fr.itii.utils.objets.ObjetEvent
import fr.itii.utils.objets.ObjetUser
import java.util.EventObject

class Database {

    fun getUserById(id: String) : ObjetUser {
        return ObjetUser()
    }

    fun getUsers() : List<ObjetUser> {
        return listOf()
    }

    fun getEvents() : List<EventObject> {
        return listOf()
    }

    fun getEventsByCity (city: String) : List<ObjetEvent> {
        return listOf()
    }

    fun getEventsByDate (date: String) : List<ObjetEvent> {
        return listOf()
    }

    fun getEventsByLocation (location: String) : List<ObjetEvent> {
        return listOf()
    }

    fun getEventsByCategory (category: String) : List<ObjetEvent> {
        return listOf()
    }

    fun getEventsByTags (tags: List<String>) : List<ObjetEvent> {
        return listOf()
    }

    fun getCitiesByMinPopulation(minPopulation: Int) : List<CitiesObjet> {
        return listOf()
    }

    fun getCitiesByMaxPopulation(maxPopulation: Int) : List<String> {
        return listOf()
    }

)



}