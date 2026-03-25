package fr.itii.utils.tables

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude

data class Events(
    @DocumentId val docId: String = "",
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
    @get:Exclude
    val location: LatLng
        get() = LatLng(latitude, longitude)
}
