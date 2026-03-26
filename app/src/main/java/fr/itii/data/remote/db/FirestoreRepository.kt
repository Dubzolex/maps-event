package fr.itii.data.remote.db

interface FirestoreRepository {

    fun toHashMap(): HashMap<String, Any?>
}