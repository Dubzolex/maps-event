package fr.itii.domain.models.collections

import com.google.firebase.firestore.DocumentId

data class Users(
    @DocumentId val docId: String = "",
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val date: String = "",
    val city: String = "",
    val address: String = ""

)