package fr.itii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.itii.ui.theme.MyApplicationTheme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import fr.itii.utils.MyMaps
import fr.itii.utils.MyNavigation
import fr.itii.utils.MyProfilAccount
import fr.itii.utils.MySearchable

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

@Composable
fun MainApp() {
    // 1. On garde l'index en mémoire ici (State Hoisting)
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            // On passe l'index et l'action de changement
            MyNavigation(
                selectedItem = selectedIndex,
                onItemSelected = { num -> selectedIndex = num }
            )
        }
    ) { innerPadding ->
        // 2. Le conteneur qui change de contenu
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedIndex) {
                0 -> MyMaps()      // Si 0, on affiche la carte
                1 -> MySearchable()   // Si 1, on affiche la recherche
                2 -> MyProfilAccount()  // Si 2, on affiche le profil
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}



