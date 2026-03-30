package fr.itii.data.remote.db

import android.util.Log
import fr.itii.domain.models.collections.Events
import fr.itii.domain.models.collections.Users
import java.util.EventObject
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import fr.itii.domain.models.enums.Table


class Database {

    private val db = FirebaseFirestore.getInstance()

    fun addUser(user: Users, onComplete: (Boolean) -> Unit) {
        // On utilise l'id (l'UID Firebase Auth) comme nom de document
        if (user.id.isEmpty()) {
            onComplete(false)
            return
        }

        db.collection("users")
            .document(user.id)
            .set(user, SetOptions.merge()) // merge() évite d'écraser les champs existants si on met à jour
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun getUser(uid: String, onResult: (Users?) -> Unit) {
        if (uid.isEmpty()) {
            onResult(null)
            return
        }

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // La magie de Firestore : conversion auto vers ta Data Class
                    val user = document.toObject(Users::class.java)
                    onResult(user)
                } else {
                    onResult(null) // Document inexistant
                }
            }
            .addOnFailureListener {
                onResult(null) // Erreur réseau ou autre
            }
    }

    fun updateUser(user: Users, onResult: (Boolean) -> Unit) {
        if (user.id.isEmpty()) {
            onResult(false)
            return
        }

        db.collection("users")
            .document(user.id)
            .set(user)
            .addOnSuccessListener {
                onResult(true) // Succès !
            }
            .addOnFailureListener { e ->
                Log.e("DATABASE", "Erreur update", e)
                onResult(false)
            }
    }





}