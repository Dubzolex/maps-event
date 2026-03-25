package fr.itii.domain.utils

import com.google.android.gms.maps.model.LatLng
import fr.itii.domain.models.collections.Events
import fr.itii.domain.utils.maps.MapPointer

class Serializer {


    fun toObjet(objet: Events) : MapPointer {
        return MapPointer(
            coordinates = LatLng(objet.latitude, objet.longitude),
            title = objet.name,
            subTitle = objet.ville + " - " + objet.adresse
        )
    }




}