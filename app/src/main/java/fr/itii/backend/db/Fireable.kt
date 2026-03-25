package fr.itii.backend.db

import fr.itii.utils.objets.ObjetCity
import fr.itii.utils.objets.ObjetEvent
import fr.itii.utils.objets.ObjetUser

interface Fireable {

    fun add(table: String, data: HashMap<String, String>)
    fun update(table: String, data: HashMap<String, String>)
    fun get(table: String): HashMap<String, String>
    fun delete(table: String, data: HashMap<String, String>)

    fun add(data: ObjetUser)
    fun update(data: ObjetUser)
    fun delete(data: ObjetUser)

    fun add(data: ObjetEvent)
    fun update(data: ObjetEvent)
    fun delete(data: ObjetEvent)

    fun add(data: ObjetCity)
    fun addAll(data: List<ObjetCity>)
    fun update(data: ObjetCity)
    fun delete(data: ObjetCity)

}