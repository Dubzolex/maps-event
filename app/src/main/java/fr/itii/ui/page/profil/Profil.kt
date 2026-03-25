package fr.itii.ui.page.profil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import fr.itii.domain.models.collections.Users


@Composable
fun Profil(
    currentUser: Users?,
    showSignUp: MutableState<Boolean>,
    onLoginSuccess: (Users) -> Unit,
    onLogout: () -> Unit
) {
    when {
        currentUser != null -> {
            Account(
                user = currentUser,
                onLogout = onLogout
            )
        }

        showSignUp.value -> {
            SignUp(
                onSignUpSuccess = onLoginSuccess,
                onBackToLogin = { showSignUp.value = false }
            )
        }

        else -> {
            Login(
                onLoginSuccess = onLoginSuccess,
                onGoToSignUp = { showSignUp.value = true }
            )
        }
    }
}