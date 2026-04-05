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
import fr.itii.ui.components.ActionButton
import fr.itii.ui.components.LinkButton
import fr.itii.ui.components.PasswordField
import fr.itii.ui.profil.UserViewModel
import fr.itii.ui.components.UserField
import androidx.compose.ui.platform.LocalContext

@Composable
fun SignIn(
    viewModel: UserViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current


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
            UserField("Email", email, onValueChange = { email = it })
            PasswordField("Mot de passe", password, onValueChange = { password = it })
        }



        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            LinkButton(
                text = "Créer un compte",
                onClick = { viewModel.currentScreen = "SignUp" }
            );

            ActionButton(
                text = "Se connecter",
                onClick = { viewModel.signIn(email, password, context) }
            )
        }
    }
}

