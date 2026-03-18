package fr.itii.backend

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import fr.itii.models.objects.User

class DBFirebase {

    val db = Firebase.firestore



    val event = hashMapOf(
        "name" to "Mon Événement",
        "location" to "Paris",
        "date" to "2026-03-20"
    )

    // La syntaxe correcte : nom : Type
    fun save(data: HashMap<String, String>) {
        db.collection("users")
            .add(data)
            .addOnSuccessListener { documentReference ->
                println("Document ajouté avec l'ID : ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Erreur lors de l'ajout : $e")
            }

        db.collection("events").add(data)

        val userParams = User("Enzo", "Lyon", "2026-04-10")
        // Avec 2 paramètres seulement
        val user1 = User(id = 1, name = "Enzo")

// Avec tous les paramètres
        val user2 = User("2", "Alice", "alice@mail.com", 25, "Paris")

// En nommant les paramètres (très lisible)
        val user3 = User(name = "Bob", city = "Lyon")
// On définit l'ID du document manuellement
        db.collection("users").document("enzo_123").set(userParams)
    }

    val data = object {
        val name = "Mon Event"
        val city = "Rouen"
    }


}