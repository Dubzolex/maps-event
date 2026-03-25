package fr.itii.ui.page.profil

import androidx.compose.runtime.Composable
import fr.itii.backend.auth.Authenticator

@Composable
fun Profil(auth: Authenticator) {
    if(auth.currentUser == null) {
        Login()
    } else {
        Account()
    }
}