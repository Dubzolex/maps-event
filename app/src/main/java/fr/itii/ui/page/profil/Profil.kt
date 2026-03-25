package fr.itii.ui.page.profil

import androidx.compose.runtime.Composable
import fr.itii.data.remote.auth.Authenticator

@Composable
fun Profil(auth: Authenticator) {
    if(auth.currentUser == null) {
        Login()
    } else {
        Account()
    }
}