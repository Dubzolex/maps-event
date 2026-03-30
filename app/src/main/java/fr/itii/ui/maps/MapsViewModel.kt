package fr.itii.ui.maps

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import fr.itii.domain.models.collections.Events


class MapsViewModel : ViewModel() {

    val eventsList = mutableStateListOf<Events>()

    val db = Firebase.firestore


    init {
        loadFakeEvents()
    }

    private fun loadFakeEvents() {
        eventsList.clear()

        eventsList.addAll(
            listOf(
                Events(
                    id = "1",
                    name = "Chez Marco",
                    date = "10/04/2026",
                    type = "Restaurant",
                    ville = "Rouen",
                    adresse = "12 rue Victor Hugo",
                    latitude = 49.4431,
                    longitude = 1.0993,
                    creator = "Mike"
                ),
                Events(
                    id = "2",
                    name = "Parc des Fleurs",
                    date = "12/04/2026",
                    type = "Parc",
                    ville = "Rouen",
                    adresse = "Avenue des arbres",
                    latitude = 49.4470,
                    longitude = 1.0930,
                    creator = "Mike"
                ),
                Events(
                    id = "3",
                    name = "Match amical",
                    date = "15/04/2026",
                    type = "Sport",
                    ville = "Rouen",
                    adresse = "Stade municipal",
                    latitude = 49.4500,
                    longitude = 1.0800,
                    creator = "Mike"
                ),
                Events(
                    id = "4",
                    name = "Pièce de théâtre",
                    date = "20/04/2026",
                    type = "Culture",
                    ville = "Rouen",
                    adresse = "Centre ville",
                    latitude = 49.4400,
                    longitude = 1.1050,
                    creator = "Mike"
                )
            )
        )



        // Dans ton MapsViewModel
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

    fun addEvent(event: Events) {
        eventsList.add(event)
    }
}
