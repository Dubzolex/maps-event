package fr.itii.models.objets

data class ObjetUser(
    val name: String = "",
    val location: String = "",
    val date: String = "",
    val id: Int ? = null,
    val city: String = ""
)
