package fr.itii.ui.profil.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.ui.profil.ProfileViewModel
import fr.itii.ui.components.ButtonAction
import fr.itii.ui.components.ButtonLink
import fr.itii.ui.components.InputField


@Composable
fun SignIn(
    viewModel: ProfileViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Connexion",
            style = MaterialTheme.typography.headlineMedium
        )

        Column(
            modifier = Modifier

                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            InputField("Email", email, onValueChange = { email = it })
            InputField("Mot de passe", password, onValueChange = { password = it })
        }

        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            ButtonLink(
                text = "Créer un compte",
                onClick = { viewModel.currentScreen = "SignUp" }
            );

            ButtonAction(
                text = "Se connecter",
                onClick = { viewModel.signIn(email, password) }
            )
        }
    }
}

