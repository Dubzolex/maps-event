package fr.itii.backend.db

import fr.itii.utils.tables.TableEvent
import fr.itii.utils.tables.TableUser
import java.util.EventObject

class Database {

    fun getUserById(id: String) : TableUser {
        return TableUser()
    }

    fun getUsers() : List<TableUser> {
        return listOf()
    }

    fun getEvents() : List<EventObject> {
        return listOf()
    }

    fun getEventsByCity (city: String) : List<TableEvent> {
        return listOf()
    }

    fun getEventsByDate (date: String) : List<TableEvent> {
        return listOf()
    }

    fun getEventsByLocation (location: String) : List<TableEvent> {
        return listOf()
    }

    fun getEventsByCategory (category: String) : List<TableEvent> {
        return listOf()
    }

    fun getEventsByTags (tags: List<String>) : List<TableEvent> {
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