package fr.itii.ui.profil

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore

import fr.itii.domain.models.collections.Users
import fr.itii.domain.models.enums.Table
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class UserRepository() {

    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    // StateFlow pour exposer les données utilisateur en temps réel
    private val userProfileStat = MutableStateFlow<Users?>(null)
    val userProfile: StateFlow<Users?> = userProfileStat

    val currentUser: FirebaseUser?
        get() = auth.currentUser


    init {
        // Si l'utilisateur est déjà loggé au démarrage, on lance l'écoute
        auth.currentUser?.uid?.let { startUserObserver(it) }
    }

    private fun startUserObserver(uid: String) {
        db.collection(Table.USER.value).document(uid).addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.e("REPO", "Erreur ecoute utilisateur", e)
                return@addSnapshotListener
            }
            userProfileStat.value = snapshot?.toObject(Users::class.java)
        }
    }

    // --- ACTIONS ---

    fun signUp(user: Users, onResult: (Boolean, String?) -> Unit) {
        if (auth.currentUser != null) {
            onResult(true, "Deja connecte.")
            return
        }

        if (user.email.isEmpty() || user.password.isEmpty()) {
            onResult(false, "Veuillez remplir tous les champs.")
            return
        }

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    user.docId = uid

                    // On enregistre dans Firestore
                    add(uid, user) { success, message ->
                        if (success) {
                            startUserObserver(uid) // On commence à écouter
                            onResult(true, "Inscription reussie.")
                        } else {
                            onResult(false, message)
                        }
                    }
                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }

    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (email.isEmpty() || password.isEmpty()) {
            onResult(false, "Veuillez remplir tous les champs.")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""
                    startUserObserver(uid)
                    onResult(true, "Connecté !")
                } else {
                    val message = when (val e = task.exception) {
                        is FirebaseAuthInvalidUserException -> {
                            "Compte inexistant !"
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            "Email ou mot de passe incorrect !"
                        }
                        else -> {
                            "Identifiants incorrects !"
                        }
                    }
                    Log.e("REPO", "Erreur connexion : ${task.exception?.localizedMessage}", task.exception)
                    onResult(false, message)
                }
            }
    }


    fun logout() {
        auth.signOut()
        userProfileStat.value = null
    }

    // Base de données FIRESTORE

    fun add(doc: String, user: Users, onResult: (Boolean, String?) -> Unit) {
        db.collection(Table.USER.value).document(doc)
            .set(user)
            .addOnSuccessListener {
                onResult(true, "Succès")
            }
            .addOnFailureListener { e ->
                Log.e("REPO USER", "Erreur add", e)
                onResult(false, "Erreur lors de la mise à jour : ${e.localizedMessage}")
            }
    }

    fun update(user: Users, onResult: (Boolean, String?) -> Unit) {
        val uid = auth.currentUser?.uid ?: return

        db.collection(Table.USER.value).document(uid)
            .set(user)
            .addOnSuccessListener {
                onResult(true, "Profil mis à jour !")
            }
            .addOnFailureListener { e ->
                Log.e("REPO USER", "Erreur update", e)
                onResult(false, "Erreur lors de la mise à jour : ${e.localizedMessage}")
            }
    }
}