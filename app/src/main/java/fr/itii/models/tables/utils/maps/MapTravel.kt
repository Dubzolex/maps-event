package fr.itii.models.tables.utils.maps

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapTravel(
    val points: MutableList<MapPointer> = mutableListOf()
) : MapElement() {

    // On peut aussi imaginer une Polyline pour relier les points
    private var road: Polyline? = null

    /**
     * Ajoute tous les marqueurs du voyage sur la carte
     */
    fun addToMap(map: GoogleMap) {
        // 1. On affiche chaque point individuellement
        points.forEach { pointer ->
            pointer.addToMap(map)
        }

        // 2. Optionnel : On dessine une ligne (Polyline) qui relie tous les points
        if (points.size >= 2) {
            val lineOptions = PolylineOptions()
                .addAll(points.map { it.coordinates }) // On extrait les LatLng
                .color(0xFF0000FF.toInt()) // Bleu
                .width(10f)

            road = map.addPolyline(lineOptions)
        }
    }

    fun addPoint(point: MapPointer, map: GoogleMap) {
        points.add(point)
        point.addToMap(map)

        // On met à jour la ligne si elle existe
        road?.points = points.map { it.coordinates }
    }

    fun removeFromMap() {
        // On retire chaque marqueur
        points.forEach { it.removeFromMap() }
        // On retire la ligne
        road?.remove()
        road = null
    }
}