package fr.itii.models.tables.utils

import com.google.android.gms.maps.model.LatLng
import fr.itii.models.tables.utils.maps.MapPointer
import fr.itii.models.tables.utils.tables.Events

class Serializer {


    fun toObjet(objet: Events) : MapPointer {
        return MapPointer(
            coordinates = LatLng(objet.latitude, objet.longitude),
            title = objet.name,
            subTitle = objet.ville + " - " + objet.adresse)
    }




}