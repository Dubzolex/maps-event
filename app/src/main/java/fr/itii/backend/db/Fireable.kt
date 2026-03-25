package fr.itii.backend.db

import fr.itii.utils.tables.TableCity
import fr.itii.utils.tables.TableEvent
import fr.itii.utils.tables.TableUser

interface Fireable {

    fun add(table: String, data: HashMap<String, String>)
    fun update(table: String, data: HashMap<String, String>)
    fun get(table: String): HashMap<String, String>
    fun delete(table: String, data: HashMap<String, String>)

    fun add(data: TableUser)
    fun update(data: TableUser)
    fun delete(data: TableUser)

    fun add(data: TableEvent)
    fun update(data: TableEvent)
    fun delete(data: TableEvent)

    fun add(data: TableCity)
    fun addAll(data: List<TableCity>)
    fun update(data: TableCity)
    fun delete(data: TableCity)

}