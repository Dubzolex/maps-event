package fr.itii.ui.profil

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.firestore
import fr.itii.data.remote.auth.Authenticator
import fr.itii.data.remote.db.Database
import fr.itii.data.remote.db.FirestoreRepository
import fr.itii.domain.models.collections.Users
import fr.itii.domain.models.enums.Table
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepository() {

    val auth =

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    val userProfile = MutableStateFlow<Users?>(null)

    fun signUp(user: Users, onResult: (Boolean, String?) -> Unit) {
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










































        auth.signUp(user.email, user.password as String) { success, error ->
            if (success) {
                val uid = auth.getUid()
                user.docId = uid

                // On utilise ta classe Database pour enregistrer
                db.addUser(user) { isSaved ->
                    if (isSaved) {
                        userProfile.value = user
                        onResult(true, null)
                    } else {
                        onResult(false, "Erreur lors de la création du profil Firestore")
                    }
                }
            } else {
                onResult(false, error)
            }
        }
    }

    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signIn(email, password as String) { success, error ->
            if (success) {
                get()

            } else {
                onResult(false, error)
            }
        }
    }

    fun get() {
        val uid = auth.getUid()
        if (uid.isNotEmpty()) {
            db.getUser(uid) { user ->
                userProfile.value = user
            }
        }
    }

    fun update(user: Users) {
        db.updateUser(user) { success ->
            if (success) {
                userProfile.value = user
            } else {
                // Gérer l'erreur de mise à jour
            }
        }
    }

    fun logout() {
        auth.logout({ success, error ->
            if (success) {
                userProfile.value = null
            } else {
                // Gérer l'erreur de déconnexion
            }
        })
    }









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
    fun add(table: Table, doc: String, user: Users) {
        db.collection(table.toString()).document(doc)
            .set(user.toHashMap() as Map<String, Any>)
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