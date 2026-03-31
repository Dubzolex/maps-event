package fr.itii.ui.profil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fr.itii.domain.models.collections.Users
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    // On récupère le flux de l'utilisateur depuis le repo
    val userProfile: StateFlow<Users?> = repository.userProfile

    // État pour afficher les messages (erreurs ou succès)
    var uiMessage by mutableStateOf<String?>(null)
        private set

    // État pour la navigation simple (Login, SignUp, Account)
    var currentScreen by mutableStateOf("SignIn")

    init {
        // Au démarrage, on vérifie si un utilisateur est déjà là
        if (repository.currentUser != null) {
            repository.get()
            currentScreen = "Account"
        }
    }

    fun signUp(user: Users) {
        // On prépare l'objet complet
        repository.signUp(user) { success, message ->
            uiMessage = message
            if (success) {
                // On pourrait aussi ajouter la ville ici via un update si besoin
                currentScreen = "Account"
            }
        }
    }

    fun signIn(email: String, password: String) {
        repository.signIn(email, password) { success, message ->
            uiMessage = message
            if (success) {
                repository.get()
                currentScreen = "Account"
            }
        }
    }

    fun logout() {
        repository.logout()
        currentScreen = "SignUp"
    }

    fun update(user: Users) {
        repository.update(user)
    }

    fun clearMessage() { uiMessage = null }
}