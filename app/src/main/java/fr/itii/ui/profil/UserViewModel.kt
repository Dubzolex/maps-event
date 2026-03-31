package fr.itii.ui.profil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fr.itii.domain.models.collections.Users
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val user: StateFlow<Users?> = repository.userProfile

    var uiMessage by mutableStateOf<String?>(null)
    private set

    // On initialise l'écran selon la présence d'un utilisateur
    var currentScreen by mutableStateOf(
        if (repository.currentUser != null) "Account" else "SignIn"
    )

    fun signUp(user: Users) {
        repository.signUp(user) { success, message ->
            uiMessage = message
            if (success) {
                // Pas besoin de fetch manuel, le Flow du repo va s'actualiser
                currentScreen = "Account"
            }
        }
    }

    fun signIn(email: String, password: String) {
        repository.signIn(email, password) { success, message ->
            uiMessage = message
            if (success) {
                currentScreen = "Account"
            }
        }
    }

    fun logout() {
        repository.logout()
        // Le repo va mettre userProfile à null, l'UI réagira via le StateFlow
        currentScreen = "SignIn"
    }

    fun update(user: Users) {
        // Simple et efficace : le repo met à jour Firestore,
        // le snapshotListener renvoie la modif, le StateFlow se met à jour, l'UI change.
        repository.update(user) { success, message ->
                uiMessage = message
        }
    }

    fun clearMessage() { uiMessage = null }
}