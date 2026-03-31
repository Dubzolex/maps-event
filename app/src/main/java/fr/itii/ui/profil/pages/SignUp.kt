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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Users
import fr.itii.ui.profil.ProfileViewModel
import fr.itii.ui.components.ButtonAction
import fr.itii.ui.components.ButtonNeutral
import fr.itii.ui.components.InputField


@Composable
fun SignUp(
    viewModel: ProfileViewModel
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
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
                InputField("Nom", name, onValueChange = { name = it })
                InputField("Date de naissance", date, onValueChange = { date = it })
                InputField("Ville", city, onValueChange = { city = it })
                InputField("Adresse", address, onValueChange = { address = it })
            }
        }

            item {

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    InputField("Email", email, onValueChange = { email = it })
                    InputField("Mot de passe", password, onValueChange = { password = it })
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
                ButtonNeutral(
                    text = "Retour",
                    onClick = { viewModel.currentScreen = "SignIn" }
                )

                // Utilisation de ton ButtonAction optimisé
                ButtonAction(
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
                        viewModel.signUp(newUser)
                    }
                )
            }
        }
    }
}
