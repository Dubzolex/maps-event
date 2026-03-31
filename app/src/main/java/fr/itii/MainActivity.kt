package fr.itii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.itii.ui.events.EventRepository
import fr.itii.ui.events.EventViewModel
import fr.itii.ui.nav.Navigation
import fr.itii.ui.events.pages.Search
import fr.itii.ui.maps.pages.Maps
import fr.itii.ui.maps.MapsViewModel
import fr.itii.ui.profil.UserViewModel
import fr.itii.ui.profil.UserRepository
import fr.itii.ui.profil.pages.Account
import fr.itii.ui.profil.pages.SignIn
import fr.itii.ui.profil.pages.SignUp
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
    var selectedIndex by remember { mutableIntStateOf(0) }

    val eventRepo = remember { EventRepository() }
    val eventViewModel: EventViewModel = viewModel { EventViewModel(eventRepo) }
    val mapsViewModel: MapsViewModel = viewModel { MapsViewModel(eventRepo) }

    val userRepo = remember { UserRepository() }
    val profilViewModel: UserViewModel = viewModel { UserViewModel(userRepo) }

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

                1 -> Search(viewModel = eventViewModel)

                2 -> {
                    when(profilViewModel.currentScreen) {
                        "SignIn" -> SignIn(viewModel = profilViewModel)
                        "SignUp" -> SignUp(viewModel = profilViewModel)
                        "Account" -> Account(viewModel = profilViewModel)
                    }
                }
            }
        }
    }
}
