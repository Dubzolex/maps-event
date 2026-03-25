package fr.itii.data.remote.db

import fr.itii.domain.models.collections.Events
import fr.itii.domain.models.collections.Users
import java.util.EventObject

class Database {

    fun getUserById(id: String) : Users {
        return Users()
    }

    fun getUsers() : List<Users> {
        return listOf()
    }

    fun getEvents() : List<EventObject> {
        return listOf()
    }

    fun getEventsByCity (city: String) : List<Events> {
        return listOf()
    }

    fun getEventsByDate (date: String) : List<Events> {
        return listOf()
    }

    fun getEventsByLocation (location: String) : List<Events> {
        return listOf()
    }

    fun getEventsByCategory (category: String) : List<Events> {
        return listOf()
    }

    fun getEventsByTags (tags: List<String>) : List<Events> {
        return listOf()
    }

    fun getCitiesByMaxPopulation(maxPopulation: Int) : List<String> {
        return listOf()
    }





}