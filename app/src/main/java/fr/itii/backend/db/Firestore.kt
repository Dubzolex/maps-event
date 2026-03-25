package fr.itii.backend.db

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import fr.itii.utils.objets.ObjetUser

class Firestore {

    val db = Firebase.firestore



    val event = hashMapOf(
        "name" to "Mon Événement",
        "location" to "Paris",
        "date" to "2026-03-20"
    )

    fun update(ta)

    fun get(table: String)

    // La syntaxe correcte : nom : Type
    fun save(table: String, data: HashMap<String, String>) {
        db.collection(table)
            .document(data["id"].toString())




            .delete()



            .update(data)

            .set(data)
            .addOnSuccessListener { documentReference ->
                println("Document ajouté avec l'ID : ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Erreur lors de l'ajout : $e")
            }

        db.collection("events").add(data)

        val userParams = ObjetUser("Enzo", "Lyon", "2026-04-10")
        // Avec 2 paramètres seulement
        val user1 = ObjetUser(id = 1, name = "Enzo")

// Avec tous les paramètres
        val user2 = ObjetUser("2", "Alice", "alice@mail.com", 25, "Paris")

// En nommant les paramètres (très lisible)
        val user3 = ObjetUser(name = "Bob", city = "Lyon")
// On définit l'ID du document manuellement
        db.collection("users").document("enzo_123").set(userParams)
    }

    val data = object {
        val name = "Mon Event"
        val city = "Rouen"
    }


}