package fr.itii.ui.events

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.firestore.FirebaseFirestore
import fr.itii.data.remote.auth.Authenticator
import fr.itii.data.remote.db.Database
import fr.itii.domain.models.collections.Events


class EventRepository(private val dbc: Database) {

    val eventsList = mutableStateListOf<Events>()
    val db = FirebaseFirestore.getInstance()


    init {
        load()
    }


    private fun load() {
        db.collection("events").addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("MapsViewModel", "Erreur : ${e.message}")
                return@addSnapshotListener
            }

            if (snapshot != null) {
                try {

                    val items = snapshot.toObjects(Events::class.java)

                    eventsList.addAll(items)
                } catch (exception: Exception) {
                    // Si ça plante encore, regarde ce log, il te dira exactement quel champ pose problème
                    Log.e("MapsViewModel", "Erreur de conversion : ", exception)
                }
            }
        }



}


}