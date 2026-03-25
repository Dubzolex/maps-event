package fr.itii.utils.tables

import com.google.android.gms.maps.model.LatLng

data class Events(
    val id: String = "",
    val name: String = "",
    val ville: String = "",
    val date: String = "",
    val adresse: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val creator: String = "",
    val type: String = ""
) {
    // Cette propriété n'est pas stockée en base, elle sert uniquement à l'affichage
    val location: LatLng
        get() = LatLng(latitude, longitude)
}
