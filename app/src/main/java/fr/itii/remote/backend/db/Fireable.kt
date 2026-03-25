package fr.itii.remote.backend.db

import fr.itii.models.tables.utils.tables.Cities
import fr.itii.models.tables.utils.tables.Events
import fr.itii.models.tables.utils.tables.Users

interface Fireable {

    fun add(table: String, data: HashMap<String, String>)
    fun update(table: String, data: HashMap<String, String>)
    fun get(table: String): HashMap<String, String>
    fun delete(table: String, data: HashMap<String, String>)

    fun add(data: Users)
    fun update(data: Users)
    fun delete(data: Users)

    fun add(data: Events)
    fun update(data: Events)
    fun delete(data: Events)

    fun add(data: Cities)
    fun addAll(data: List<Cities>)
    fun update(data: Cities)
    fun delete(data: Cities)

}