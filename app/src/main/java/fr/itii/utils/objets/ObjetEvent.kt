package fr.itii.utils.objets

data class ObjetEvent(
    val name: String = "",
    val ville: String = "",
    val date: String = "",
    val adresse: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
