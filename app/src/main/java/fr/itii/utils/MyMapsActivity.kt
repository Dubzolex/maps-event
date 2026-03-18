import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*




@Composable
fun MyMapsAct() {
    // 1. Définir la position initiale (ex: Rouen ou Paris)
    val rouen = LatLng(49.4431, 1.0993)

    // 2. Créer l'état de la caméra (pour zoomer/déplacer)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(rouen, 12f)
    }

    // 3. Afficher la carte
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = true), // Optionnel: voir sa position
        onMapClick = { latLng ->
            // Ici tu peux ajouter la logique Firestore : save(latLng)
            println("Clic sur la carte à : $latLng")
        }
    ) {
        // 4. Ajouter un Marqueur interactif
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
            fillColor = androidx.compose.ui.graphics.Color.Red.copy(alpha = 0.2f),
            strokeColor = androidx.compose.ui.graphics.Color.Red,
            strokeWidth = 2f
        )
    }
}
