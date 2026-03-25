package fr.itii.utils.tables

import com.google.android.gms.maps.model.LatLng

data class TableEvent(
    val name: String = "",
    val ville: String = "",
    val date: String = "",
    val adresse: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val location: LatLng = LatLng(latitude, longitude)
)
