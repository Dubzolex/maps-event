package fr.itii.domain.models.collections

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude

data class Events(
    @DocumentId val docId: String = "",
    var name: String = "",
    var description: String = "",
    var type: String = "",
    var date: String = "",
    var ville: String = "",
    var adresse: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,

) {
    // Cette propriété n'est pas stockée en base, elle sert uniquement à l'affichage
    @get:Exclude
    val location: LatLng
        get() = LatLng(latitude, longitude)
}