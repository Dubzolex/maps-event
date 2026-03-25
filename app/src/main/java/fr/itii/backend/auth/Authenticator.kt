package fr.itii.backend.auth

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Authenticator {

    private val auth = FirebaseAuth.getInstance()

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    fun signUp(email: String, password: String) {
        if (this.currentUser != null) {
            Log.w("AUTH", "Utilisateur déjà connecté : ${currentUser?.email}")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AUTH", "Utilisateur créé : ${currentUser?.email}")
                } else {
                    Log.w("AUTH", "Erreur d'inscription", task.exception)
                }
            }
    }

    fun login(email: String, password: String) {
        if (this.currentUser != null) {
            Log.i("AUTH", "Utilisateur déjà connecté : ${currentUser?.email}")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("AUTH", "Utilisateur connecté : ${currentUser?.email}")
                } else {
                    Log.w("AUTH", "Erreur de connexion", task.exception)
                }
            }
    }

    fun logout() {
        auth.signOut()
        Log.i("AUTH", "Utilisateur déconnecté")
    }

    // Cette fonction est maintenant redondante car tu as la propriété 'currentUser'
    /*fun getCurrentUser(): FirebaseUser? {
        return null
        //this.currentUser
    }*/
}