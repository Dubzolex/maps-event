package fr.itii.models.objects

data class User(
    val name: String = "",
    val location: String = "",
    val date: String = "",
    val id: Int ? = null,
    val city: String = ""
)
