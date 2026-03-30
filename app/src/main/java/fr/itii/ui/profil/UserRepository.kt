package fr.itii.ui.profil

import com.google.firebase.auth.FirebaseUser
import fr.itii.data.remote.auth.Authenticator
import fr.itii.data.remote.db.Database
import fr.itii.domain.models.collections.Users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepository(private val db: Database, private val auth: Authenticator) {

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    private val _userProfile = MutableStateFlow<Users?>(null)
    val userProfile: StateFlow<Users?> = _userProfile

    fun signUp(user: Users, onResult: (Boolean, String?) -> Unit) {
        auth.signUp(user.email, user.password as String) { success, error ->
            if (success) {
                val uid = auth.getUid()

                // On utilise ta classe Database pour enregistrer
                db.addUser(user) { isSaved ->
                    if (isSaved) {
                        _userProfile.value = user
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
                val uid = auth.getUid()

            } else {
                onResult(false, error)
            }
        }
    }

    fun get() {
        val uid = auth.getUid()
        if (uid.isNotEmpty()) {
            db.getUser(uid) { user ->
                _userProfile.value = user
            }
        }
    }

    fun update(user: Users) {
        db.updateUser(user) { success ->
            if (success) {
                _userProfile.value = user
            } else {
                // Gérer l'erreur de mise à jour
            }
        }
    }

    fun logout() {
        auth.logout({ success, error ->
            if (success) {
                _userProfile.value = null
            } else {
                // Gérer l'erreur de déconnexion
            }
        })
    }
}