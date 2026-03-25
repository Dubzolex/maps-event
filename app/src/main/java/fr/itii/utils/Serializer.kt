package fr.itii.utils

import com.google.android.gms.maps.model.LatLng
import fr.itii.utils.maps.MapPointer
import fr.itii.utils.tables.TableEvent

class Serializer {


    fun toObjet(objet: TableEvent) : MapPointer {
        return MapPointer(
            coordinates = LatLng(objet.latitude, objet.longitude),
            title = objet.name,
            subTitle = objet.ville + " - " + objet.adresse)
    }




}