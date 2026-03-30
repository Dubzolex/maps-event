package fr.itii.data.remote.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Authenticator {
    private val auth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (this.currentUser != null) {
            Log.w("AUTH", "Utilisateur déjà connecté : ${currentUser?.email}")
            onResult(true, "Déjà connecté.")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AUTH", "Utilisateur créé : ${currentUser?.email}")
                    onResult(true, "Compte créé avec succès.")
                } else {
                    Log.w("AUTH", "Erreur d'inscription", task.exception)
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }

    fun signIn(email: String, password: String,  onResult: (Boolean, String?) -> Unit) {
        if (this.currentUser != null) {
            Log.i("AUTH", "Utilisateur déjà connecté : ${currentUser?.email}")
            onResult(true, "Déjà connecté.")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AUTH", "Utilisateur connecté : ${currentUser?.email}")
                    onResult(true, "Utilisateur connecté.")
                } else {
                    Log.w("AUTH", "Erreur de connexion", task.exception)
                    onResult(false, "Erreur de connexion.")
                }
            }
    }

    fun logout(onResult: (Boolean, String?) -> Unit) {
        try {
            auth.signOut()
            Log.i("AUTH", "Utilisateur déconnecté")
            onResult(true, "Utilisateur déconnecté.")
        } catch (e: Exception) {
            Log.w("AUTH", "Erreur de déconnexion", e)
            onResult(false, e.localizedMessage)
        }
    }

    fun getUid(): String = auth.currentUser?.uid ?: ""

}