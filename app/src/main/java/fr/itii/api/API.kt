package fr.itii.api

import com.google.firebase.auth.FirebaseUser
import fr.itii.backend.auth.Authenticator

class API {

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        var auth = Authenticator()
        return auth.login(email, password)
    }

    fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        var auth = Authenticator()
        return auth.signUp(email, password)
    }

    fun logout() {
        var auth = Authenticator()
        return auth.logout()
    }

    fun getUser(): {

        var auth = Authenticator()
        val user = auth.getCurrentUser()

        val db = Database()

        var account =
    }
}