package fr.itii.ui.page.maps

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.Firebase
import fr.itii.utils.tables.TableEvent

class MapsViewModel {

    // 1. L'état réactif de l'UI : Compose va écouter cette liste
    // Nous stockons directement les TableEvent pour avoir toutes les infos
    val eventsList = mutableStateListOf<TableEvent>()



    init {
        // Au lancement du ViewModel, on commence à écouter
        fetchEventsFromDatabase()
    }

    private fun fetchEventsFromDatabase() {
        // 2. Écouter Firestore en TEMPS RÉEL
        db.collection("events") // Ou utiliser ton Enum: Table.EVENTS.nameTable
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("MapViewModel", "Erreur lors de l'écoute", e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    // 3. Convertir les documents Firestore en objets TableEvent
                    val updatedEvents = snapshot.toObjects()

                    // 4. Mettre à jour la liste réactive
                    eventsList.clear() // On efface l'ancienne liste
                    eventsList.addAll(updatedEvents) // On ajoute les nouveaux événements
                    Log.d("MapViewModel", "${eventsList.size} événements chargés.")
                }
            }
    }
}
