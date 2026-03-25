package fr.itii.backend.db.test

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import fr.itii.utils.Table
import fr.itii.utils.objets.ObjetUser

class Firestore {



class Firestore {
    val db = Firebase.firestore


    fun add(table: Table, doc: String, data: HashMap<String, String>) {
        db.collection(table.toString()).document(doc)
                .set(data)
                .addOnSuccessListener { documentReference ->
                    println("Document ajouté avec l'ID : ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Erreur lors de l'ajout : $e")
                }
    }

    fun add(user: ObjetUser) {
        add(Table.USER, user.id.toString(), ObjetUser => HashMap())
    }




























    fun add(table: String, data: HashMap<String, String>) {
        db.collection(table).add(data)
    }
    // type data class
    fun add(data: ObjetUser)  {
        db.collection("users").add(data)

    }
}

}

