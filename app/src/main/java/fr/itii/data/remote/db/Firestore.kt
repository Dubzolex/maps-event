package fr.itii.data.remote.db

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import fr.itii.domain.models.enums.Table



class Firestore {

    // Interface commun

    val db = Firebase.firestore

    // Objet User
    data class Users(
        val id: String = "",
        val name: String = "",
        val email: String = ""
    ) : FirestoreRepository {
        override fun toHashMap(): HashMap<String, Any?> {
            return hashMapOf(
                "id" to id,
                "name" to name,
                "email" to email
            )
        }
    }

    //Objet Event
    data class Events(
        val id: String = "",
        val name: String = "",
        val city: String = "",
        val date: String = "", // Idéalement, utilise un Timestamp Firebase ici
        val address: String = "",
        val type: String = "",
        val description: String = ""
    ) : FirestoreRepository {
        override fun toHashMap(): HashMap<String, Any?> {
            return hashMapOf(
                "id" to id,
                "name" to name,
                "city" to city,
                "date" to date,
                "address" to address,
                "type" to type,
                "description" to description
            )
        }
    }


    // Fonction centrale (Add)
    fun add(table: Table, doc: String, obj: FirestoreRepository) {
        db.collection(table.toString()).document(doc)
            .set(obj.toHashMap())
            .addOnSuccessListener {
                println("Ajout réussi dans $table")
            }
            .addOnFailureListener { e ->
                println("Erreur : $e")
            }
    }

    // Fonction centrale (update)
    fun update(table: Table, doc: String, obj: FirestoreRepository) {
        db.collection(table.nameTable).document(doc)
            .update(obj.toHashMap() as Map<String, Any>)
            .addOnSuccessListener {
                println("Update réussi dans $table")
            }
            .addOnFailureListener { e ->
                println("Erreur update : $e")
            }
    }

    // Fonction centrale (Delate)
    fun delete(table: Table, doc: String) {
        db.collection(table.nameTable).document(doc)
            .delete()
            .addOnSuccessListener {
                println("Suppression réussie dans $table")
            }
            .addOnFailureListener { e ->
                println("Erreur suppression : $e")
            }
    }

    //  GET UN DOCUMENT
    fun get(table: Table, doc: String, onResult: (Map<String, Any>?) -> Unit) {
        db.collection(table.nameTable).document(doc)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    onResult(document.data)
                } else {
                    onResult(null)
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    // GET TOUS LES DOCUMENTS
    fun getAll(table: Table, onResult: (List<Map<String, Any>>) -> Unit) {
        db.collection(table.name)
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<Map<String, Any>>()

                for (doc in result) {
                    doc.data?.let { list.add(it) }
                }

                onResult(list)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }
    }
}
