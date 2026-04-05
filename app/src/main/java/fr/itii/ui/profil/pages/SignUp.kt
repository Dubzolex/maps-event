package fr.itii.ui.profil.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Users
import fr.itii.ui.components.ActionButton
import fr.itii.ui.profil.UserViewModel
import fr.itii.ui.components.NeutralButton
import fr.itii.ui.components.PasswordField
import fr.itii.ui.components.UserField


@Composable
fun SignUp(
    viewModel: UserViewModel
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val context = LocalContext.current



    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text(
                text = "Créer un compte",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        // On groupe les inputs dans des items séparés pour la performance
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                UserField("Nom", name, onValueChange = { name = it })
                UserField("Date de naissance", date, onValueChange = { date = it })
                UserField("Ville", city, onValueChange = { city = it })
                UserField("Adresse", address, onValueChange = { address = it })
            }
        }

        item {

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                UserField("Email", email, onValueChange = { email = it })
                PasswordField("Mot de passe", password, onValueChange = { password = it })
            }

        }

        item {
            // Utilisation d'une Row pour les boutons
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Un bouton plus simple pour le retour
                NeutralButton(
                    text = "Retour",
                    onClick = { viewModel.currentScreen = "SignIn" }
                )

                // Utilisation de ton ButtonAction optimisé
                ActionButton(
                    text = "S'inscrire",
                    onClick = {
                        val newUser = Users(
                            "",
                            name,
                            email,
                            password,
                            date,
                            city,
                            address)
                        viewModel.signUp(newUser, context)
                    }
                )
            }
        }
    }
}
