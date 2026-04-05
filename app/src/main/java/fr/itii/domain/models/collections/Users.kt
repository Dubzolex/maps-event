package fr.itii.domain.models.collections

import com.google.firebase.firestore.DocumentId

data class Users(
    @DocumentId var docId: String = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var date: String = "",
    var city: String = "",
    var address: String = ""
)