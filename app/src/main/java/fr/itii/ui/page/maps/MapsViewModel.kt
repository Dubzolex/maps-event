package fr.itii.ui.page.maps

import androidx.compose.runtime.mutableStateListOf
import fr.itii.models.tables.utils.tables.Events


import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MapsViewModel : ViewModel() {

    private val db: FirebaseFirestore = Firebase.firestore

    // Une liste réactive d'événements que Compose va observer
    // Utiliser mutableStateListOf permet à Compose de détecter les ajouts/suppressions
    val eventsList = mutableStateListOf<Events>()

    init {
        // On lance la récupération dès que le ViewModel est créé
        loadEvents()
    }

    private fun loadEvents() {
        // On écoute la collection "events" (ou utilise ton Enum Table.EVENTS.nameTable)

        eventsList.clear()

        eventsList.addAll(
            listOf(
                Events(
                    id = "1",
                    name = "Concert Rouen",
                    date = "10/04/2026",
                    type = "Concert",
                    ville = "Rouen",
                    adresse = "12 rue Victor Hugo",
                    latitude = 49.4431,
                    longitude = 1.0993,
                    creator = "Mike"
                ),
                Events(
                    id = "2",
                    name = "Match de foot",
                    date = "15/04/2026",
                    type = "Sport",
                    ville = "Rouen",
                    adresse = "Stade municipal",
                    latitude = 49.4500,
                    longitude = 1.0800,
                    creator = "Mike"
                ),
                Events(
                    id = "3",
                    name = "Pièce théâtre",
                    date = "20/04/2026",
                    type = "Théâtre",
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
}

