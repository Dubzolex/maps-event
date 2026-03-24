package fr.itii.models.maps

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng


class MapZone(
    var center: LatLng,
    var radius: Double, // Le rayon est en mètres
    var title: String = "Zone Sans Nom"
) : MapElement() {

    private var googleCircle: Circle? = null

    /**
     * Ajoute le cercle sur la carte Google Maps
     */
    fun addToMap(map: GoogleMap) {
        val options = CircleOptions()
            .center(this.center)
            .radius(this.radius)
            .strokeWidth(5f)              // Épaisseur du contour
            .strokeColor(0xFFFF0000.toInt()) // Rouge pour le contour
            .fillColor(0x44FF0000)        // Rouge transparent (Hex: 44) pour l'intérieur
            .clickable(true)              // Permet de cliquer sur la zone

        this.googleCircle = map.addCircle(options)

        // On peut aussi attacher l'objet MapZone au cercle via le Tag
        this.googleCircle?.tag = this
    }

    /**
     * Supprime la zone de la carte
     */
    fun removeFromMap() {
        googleCircle?.remove()
        googleCircle = null
    }

    /**
     * Met à jour dynamiquement le rayon
     */
    fun updateRadius(newRadius: Double) {
        this.radius = newRadius
        googleCircle?.radius = newRadius
    }

    /**
     * Met à jour la position du centre (si la zone est mobile)
     */
    fun updateCenter(newCenter: LatLng) {
        this.center = newCenter
        googleCircle?.center = newCenter
    }

    /**
     * Change la couleur de la zone
     */
    fun setZoneColor(fillColor: Int, strokeColor: Int) {
        googleCircle?.fillColor = fillColor
        googleCircle?.strokeColor = strokeColor
    }
}
