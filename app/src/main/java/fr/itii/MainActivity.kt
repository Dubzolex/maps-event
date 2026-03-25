package fr.itii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.itii.domain.models.collections.Events
import fr.itii.domain.models.collections.Users
import fr.itii.ui.nav.Navigation
import fr.itii.ui.page.events.EventDetails
import fr.itii.ui.page.events.MySearchable
import fr.itii.ui.page.maps.Maps
import fr.itii.ui.page.maps.MapsViewModel
import fr.itii.ui.page.profil.Profil
import fr.itii.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp() {
    val mapsViewModel: MapsViewModel = viewModel()

    var selectedIndex by remember { mutableIntStateOf(0) }

    // Gestion utilisateur très simple pour l'instant
    var currentUser by remember { mutableStateOf<Users?>(null) }
    val showSignUp = remember { mutableStateOf(false) }

    // Pour afficher un détail d'event depuis la page recherche
    var selectedSearchEvent by remember { mutableStateOf<Events?>(null) }

    Scaffold(
        bottomBar = {
            Navigation(
                selectedItem = selectedIndex,
                onItemSelected = { index -> selectedIndex = index }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedIndex) {
                0 -> Maps(viewModel = mapsViewModel)

                1 -> MySearchable(
                    eventsList = mapsViewModel.eventsList,
                    onEventClick = { eventse: Events ->
                        selectedSearchEvent = eventse
                    }
                )

                2 -> Profil(
                    currentUser = currentUser,
                    showSignUp = showSignUp,
                    onLoginSuccess = { user ->
                        currentUser = user
                    },
                    onLogout = {
                        currentUser = null
                        showSignUp.value = false
                    }
                )
            }
        }
    }

    // Détail d'un event quand on clique dans Search
    if (selectedSearchEvent != null) {
        ModalBottomSheet(
            onDismissRequest = { selectedSearchEvent = null }
        ) {
            EventDetails(
                event = selectedSearchEvent!!,
                onClose = { selectedSearchEvent = null }
            )
        }
    }
}