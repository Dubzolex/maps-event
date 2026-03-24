package fr.itii.models.maps

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapPointer(
    var coordinates: LatLng,
    var title: String,
    var subTitle: String
) : MapElement() {

    private var markerGoogle: Marker? = null

    fun addToMap(map: GoogleMap): Marker? {
        val options = MarkerOptions()
            .position(this.coordinates)
            .title(this.title)
            .snippet(this.subTitle)

        this.markerGoogle = map.addMarker(options)
        return this.markerGoogle
    }

    fun removeFromMap() {
        markerGoogle?.remove() // Le ?. remplace le "if != null"
        markerGoogle = null
    }
/*
    fun setTitle(value: String) {
        this.title = value
        markerGoogle?.title = value // Mise à jour visuelle immédiate
    }*//*

    fun setSubTitle(value: String) {
        this.subTitle = value
        markerGoogle?.snippet = value
    }*/

}
