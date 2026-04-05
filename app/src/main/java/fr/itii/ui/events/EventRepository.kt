package fr.itii.ui.events

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import fr.itii.domain.models.collections.Events
import fr.itii.domain.models.collections.Users
import fr.itii.domain.models.enums.Table
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EventRepository() {

    val db = FirebaseFirestore.getInstance()
    private val eventsListStat = MutableStateFlow<List<Events>>(emptyList())
    val eventsList: StateFlow<List<Events>> = eventsListStat

    init {
        load()
    }
    private fun load() {
        db.collection(Table.EVENT.value).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("MapsViewModel", "Erreur : ${e.message}")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                try {
                    val items = snapshot.toObjects(Events::class.java)
                    eventsListStat.value = items

                } catch (exception: Exception) {
                    // Si ça plante encore, regarde ce log, il te dira exactement quel champ pose problème
                    Log.e("MapsViewModel", "Erreur de conversion : ", exception)
                }
            }
        }
    }

    fun filter(list: List<Events>, value: String, selector: (Events) -> String
        ): List<Events> {
            return list.filter { event ->
                selector(event).contains(value, ignoreCase = true)
            }
    }

    fun add(event: Events, onResult: (Boolean, String?) -> Unit) {
        db.collection(Table.EVENT.value)
            .add(event)
            .addOnSuccessListener {
                onResult(true, "Evenement ajoute !")
            }
            .addOnFailureListener { e ->
                Log.e("REPO EVENT", "Erreur add : ${e.localizedMessage}", e)
                onResult(false, "Erreur lors de l'ajout.")
            }
    }

    fun update(event: Events, onResult: (Boolean, String?) -> Unit) {
        db.collection(Table.EVENT.value).document(event.docId)
            .set(event)
            .addOnSuccessListener {
                onResult(true, "Evenement mis à jour !")
            }
            .addOnFailureListener { e ->
                Log.e("REPO EVENT", "Erreur update : ${e.localizedMessage}", e)
                onResult(false, "Erreur lors de la mise à jour.")
            }
    }

    fun delete(event: Events, onResult: (Boolean, String?) -> Unit) {
        db.collection(Table.EVENT.value).document(event.docId)
            .delete()
            .addOnSuccessListener {
                onResult(true, "Evenement supprimé !")
            }
            .addOnFailureListener { e ->
                Log.e("REPO EVENT", "Erreur delete : ${e.localizedMessage}", e)
                onResult(false, "Erreur lors de la suppression.")
            }
    }

}