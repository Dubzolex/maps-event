package fr.itii.ui.page.profil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.itii.domain.models.collections.Users


@Composable
fun SignUp(
    onSignUpSuccess: (Users) -> Unit,
    onBackToLogin: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Créer un compte")

        SignUpField("Nom", name)
        SignUpField("Email", email)
        SignUpField("Ville", city)
        SignUpField("Mot de passe", password)

        Button(
            onClick = {
                val newUser = Users(
                    id = "2",
                    name = name.value,
                    email = email.value,
                    city = city.value
                )
                onSignUpSuccess(newUser)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Créer le compte")
        }

        Button(
            onClick = onBackToLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Retour connexion")
        }
    }
}

@Composable
private fun SignUpField(
    label: String,
    state: MutableState<String>
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}