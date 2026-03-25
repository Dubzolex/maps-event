package fr.itii.ui.page.maps

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*



@Composable
fun Maps(viewModel: MapsViewModel) {
    // 1. Définir la position initiale (ex: Rouen ou Paris)
    val events = viewModel.eventsList

    val rouen = LatLng(1.2, 2.4)

    // 2. Créer l'état de la caméra (pour zoomer/déplacer)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(49.4431, 1.0993), 10f)
    }

    // 3. Afficher la carte
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = false), // Optionnel: voir sa position
        onMapClick = { latLng ->
            // Ici tu peux ajouter la logique Firestore : save(latLng)
            println("Clic sur la carte à : $latLng")
        }
    ) {
                // C'est ici que la magie opère :
                events.forEach { event ->
                    Marker(
                        state = MarkerState(position = event.location),
                        title = event.name,
                        snippet = "${event.ville} - ${event.date}",
                        onClick = {
                            Log.d("MAP", "Clic sur : ${event.name}")
                            false // Retourner false pour laisser le comportement par défaut (centrer + info window)
                        }
                    )
                }
            }
        }

/*// 4. Ajouter un Marqueur interactif
Marker(
    state = MarkerState(position = rouen),
    title = "Ma Position",
    snippet = "Je suis ici !",
    onClick = {
        println("Marqueur cliqué !")
        false
    }
)

// 5. Ajouter un Cercle de zone
Circle(
    center = rouen,
    radius = 1000.0, // 1km
    fillColor = Color.Red.copy(alpha = 0.2f),
    strokeColor = Color.Red,
    strokeWidth = 2f
)*/
