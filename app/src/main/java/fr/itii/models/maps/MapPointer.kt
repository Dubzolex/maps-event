package fr.itii.models.maps

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.CircleOptions

class MapPointer {


    val polyline = googleMap.addPolyline(
        PolylineOptions()
            .add(LatLng(48.85, 2.35), LatLng(48.81, 2.39)) // Ajoute tes points ici
            .width(10f)              // Épaisseur
            .color(Color.RED)        // Couleur (import android.graphics.Color)
            .geodesic(true)          // Suit la courbure de la terre
    )

    val paris = LatLng(48.8566, 2.3522)

    val marker = googleMap.addMarker(
        MarkerOptions()
            .position(paris)
            .title("Marker à Paris")
            .snippet("Ma super description")
    )

    val circle = googleMap.addCircle(
        CircleOptions()
            .center(paris)
            .radius(1000.0)             // Rayon en mètres
            .strokeColor(Color.BLUE)    // Couleur du bord
            .fillColor(0x220000FF)      // Bleu transparent (format ARGB)
            .strokeWidth(5f)
    )
}