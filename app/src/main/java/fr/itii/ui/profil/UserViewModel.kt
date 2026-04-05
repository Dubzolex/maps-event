package fr.itii.ui.profil

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import fr.itii.domain.models.collections.Users
import kotlinx.coroutines.flow.StateFlow

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val user: StateFlow<Users?> = repository.userProfile

    // On initialise l'écran selon la présence d'un utilisateur
    var currentScreen by mutableStateOf(
        if (repository.currentUser != null) "Account" else "SignIn"
    )

    fun signUp(user: Users, context: Context) {
        repository.signUp(user) { success, message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            if (success) {
                currentScreen = "Account"
            }
        }
    }

    fun signIn(email: String, password: String, context: Context) {
        repository.signIn(email, password) { success, message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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

    fun update(user: Users, context: Context) {
        repository.update(user) { success, message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}